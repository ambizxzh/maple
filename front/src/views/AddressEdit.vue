<template>
    <van-address-edit
            :area-list="areaList"
            show-delete
            :address-info="addressInfo"
            save-button-text="修改"
            @save="onSave"
            @delete="onDelete"
    />
</template>

<script>
    import { Toast } from 'vant';
    import AreaList from '../api/area'
    export default {
        name: "AddressEdit",
        created() {
            let data=JSON.parse(this.$route.query.item);//取出该项目路由($route而非$router)的query中的值.
            // this后面加$代表取全局的,不加则是取本个vue文件下的
            console.log(data)
            this.addressInfo=data;
            //由于详细地址在两个页面中的关键字不同,所以进行进一步处理
            let index=data.address.indexOf('区');
            if(index<0) index=data.address.indexOf('县');
            this.addressInfo.addressDetail=data.address.substring(index+1);

        },
        data() {
            return {
                areaList:AreaList,
                addressInfo:{
                    areaCode:'',
                    name:'',
                    tel:'',
                    addressDetail:''
                }
            };
        },
        methods: {
            onSave() {
                let instance = Toast('修改成功');//弹窗
                setTimeout(()=>{
                    instance.close();//关闭弹窗
                    this.$router.push('/addressList');//this指vue项目,$指取地址,
                    // router是该项目中的router文件夹,里面的index.js设置了路由操作:Vue.use(VueRouter)
                },1000)//setTimeout设置了1000ms的时间,时间过后执行括号内的内容
            },
            onDelete() {
                history.go(-1)//往后退一页,退回到上一页
            },
        },
    };
</script>

<style scoped>

</style>
