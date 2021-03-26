package com.zxzh.shop.service.impl;

import com.zxzh.shop.entity.PhoneCategory;
import com.zxzh.shop.entity.PhoneInfo;
import com.zxzh.shop.entity.PhoneSpecs;
import com.zxzh.shop.enums.ExceptionEnum;
import com.zxzh.shop.exception.StockLackException;
import com.zxzh.shop.repository.PhoneCategoryRepository;
import com.zxzh.shop.repository.PhoneInfoRepository;
import com.zxzh.shop.repository.PhoneSpecsRepository;
import com.zxzh.shop.service.PhoneService;
import com.zxzh.shop.util.TypeConversionUtil;
import com.zxzh.shop.vo.phone.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j//打印日志
public class PhoneServiceImpl implements PhoneService {
    @Autowired
    private PhoneCategoryRepository phoneCategoryRepository;
    @Autowired
    private PhoneInfoRepository phoneInfoRepository;
    @Autowired
    private PhoneSpecsRepository phoneSpecsRepository;
    @Override
    public TopDataVO findDataVO() {
        TopDataVO topDataVO =new TopDataVO();
        //封装  类型:categories
        //方法一,传统封装方法
//        List<PhoneCategory> phoneCategoryList=phoneCategoryRepository.findAll();
//        for(PhoneCategory phoneCategory:phoneCategoryList){
//            PhoneCategoryVO phoneCategoryVO=new PhoneCategoryVO();
//            BeanUtils.copyProperties(phoneCategory,phoneCategoryVO);//同属性同名的值的传递
//            phoneCategoryVO.setCategoryName(phoneCategory.getCategoryName());
//            phoneCategoryVO.setCategoryType(phoneCategory.getCategoryType());
//        }
        //方法二,数据流封装方法; lambda 表达式 e代表super(引用)phoneCategoryList中元素实体(父类)PhoneCategory
        List<PhoneCategory> phoneCategoryList=phoneCategoryRepository.findAll();
        List<PhoneCategoryVO> phoneCategoryVOList=phoneCategoryList.stream()
                .map(e -> new PhoneCategoryVO(e.getCategoryName(),
                        e.getCategoryType()
                )).collect(Collectors.toList());
        topDataVO.setCategories(phoneCategoryVOList);
        //封装  手机:phones
        List<PhoneInfo> phoneInfoList=phoneInfoRepository.findAllByCategoryType(phoneCategoryList.get(0).getCategoryType());
        List<TopPhoneInfoVO> topPhoneInfoVOList =phoneInfoList.stream()
                .map(e -> new TopPhoneInfoVO(
                        e.getPhoneId(),
                        e.getPhoneName(),
                        e.getPhonePrice()+".00",
                        e.getPhoneDescription(),
                        TypeConversionUtil.stringToTagList(e.getPhoneTag()),
                        e.getPhoneIcon()
                )).collect(Collectors.toList());
        topDataVO.setPhones(topPhoneInfoVOList);
        return topDataVO;
    }

    @Override
    public List<TopPhoneInfoVO> findPhoneInfoVOByCategoryType(Integer categoryType) {
        List<PhoneInfo> phoneInfoList=phoneInfoRepository.findAllByCategoryType(categoryType);
        List<TopPhoneInfoVO> topPhoneInfoVOList =phoneInfoList.stream()
                .map(e->new TopPhoneInfoVO(
                        e.getPhoneId(),
                        e.getPhoneName(),
                        e.getPhonePrice()+".00",
                        e.getPhoneDescription(),
                        TypeConversionUtil.stringToTagList(e.getPhoneTag()),
                        e.getPhoneIcon()
                ))
                .collect(Collectors.toList());

        return topPhoneInfoVOList;
    }

    @Override
    public TopSpecsVO findTopSpecsVOByPhoneId(Integer phoneId) {
        PhoneInfo phoneInfo=phoneInfoRepository.findById(phoneId).get();
        List<PhoneSpecs> phoneSpecsList=phoneSpecsRepository.findAllByPhoneId(phoneId);

        //tree_v  两一一对应的链表项
        List<PhoneSpecsForSkuTreeVO> phoneSpecsForSkuTreeVOList=new ArrayList<>();//v的内容,规格
        //list
        List<ListVO> listVOList=new ArrayList<>();//list的内容,规格id
        PhoneSpecsForSkuTreeVO phoneSpecsForSkuTreeVO;
        ListVO listVO;
        for (PhoneSpecs phoneSpecs : phoneSpecsList) {//数据源
            phoneSpecsForSkuTreeVO=new PhoneSpecsForSkuTreeVO();
            listVO=new ListVO();
            BeanUtils.copyProperties(phoneSpecs,phoneSpecsForSkuTreeVO);
            BeanUtils.copyProperties(phoneSpecs,listVO);
            phoneSpecsForSkuTreeVOList.add(phoneSpecsForSkuTreeVO);
            listVOList.add(listVO);
        }
        //tree
        TreeVO treeVO=new TreeVO();
        treeVO.setV(phoneSpecsForSkuTreeVOList);
        List<TreeVO> treeVOList=new ArrayList<>();
        treeVOList.add(treeVO);
        //sku
        SkuVO skuVO=new SkuVO();
        skuVO.setTree(treeVOList);
        skuVO.setList(listVOList);
        Integer price=phoneInfo.getPhonePrice().intValue();
        skuVO.setPrice(price+".00");
        skuVO.setStock_num(phoneInfo.getPhoneStock());

        //TopSpecsVO 即data
        TopSpecsVO topSpecsVO=new TopSpecsVO();
        topSpecsVO.setSku(skuVO);
        Map<String ,String> goods=new HashMap<>();
        goods.put("pictures",phoneInfo.getPhoneIcon());
        topSpecsVO.setGoods(goods);
        return topSpecsVO;
    }

    @Override
    public void updateStock(Integer specsId, Integer quantity) {
        PhoneSpecs phoneSpecs=phoneSpecsRepository.findById(specsId).get();
        PhoneInfo phoneInfo=phoneInfoRepository.findById(phoneSpecs.getPhoneId()).get();
        //某一规格的库存
        Integer result=phoneSpecs.getSpecsStock()-quantity;
        if(result<0){
            log.error("[扣库存] 库存不足");
            throw new StockLackException(ExceptionEnum.PHONE_STOCK_ERROR);
        }
        phoneSpecs.setSpecsStock(result);
        phoneSpecsRepository.save(phoneSpecs);

        //总库存
        result=phoneInfo.getPhoneStock()-quantity;
        if(result<0){
            log.error("[扣库存] 库存不足");
            throw new StockLackException(ExceptionEnum.PHONE_STOCK_ERROR);
        }
        phoneInfo.setPhoneStock(result);
        phoneInfoRepository.save(phoneInfo);
    }


}
