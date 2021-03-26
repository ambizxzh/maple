<template>
  <div>

    <van-row>
      <van-col span="24">
        <van-tabs @click="onClick" sticky title-active-color="#E32DAB" color="#E32DAB" :line-width="100" :line-height="2">
          <!--顶部表头tab(table的缩写), index从1开始-->
          <van-tab v-for="index in categories.length" :title="categories[index-1].name" class="tab">
            <!--商品卡片-->
            <van-card v-for="(item,index) in phones"
                      :price="item.price"
                      :desc="item.desc"
                      :title="item.title"
                      :thumb="item.thumb"
            >
              <template #tags>
                <!--商品标签-->
                <van-tag v-for="tag in item.tag" color="#f2826a" style="margin-left: 5px;">{{tag.name}}</van-tag>
              </template>
              <template #footer>
                <!--购买按钮-->
                <van-button round type="info" size="mini" @click="buy(index)">购买</van-button>
              </template>
            </van-card>

          </van-tab>
        </van-tabs>
      </van-col>
    </van-row>

    <!--商品规格-->
    <van-sku
            v-model="show"
            :sku="sku"
            :goods="goods"
            :hide-stock="sku.hide_stock"
            @buy-clicked="onBuyClicked"
    >
      <template #sku-actions="props">
        <div class="van-sku-actions">

          <!-- 直接触发 sku 内部事件，通过内部事件执行 onBuyClicked 回调 -->
          <van-button
                  square
                  size="large"
                  type="danger"
                  @click="props.skuEventBus.$emit('sku:buy')"
          >
            立即购买
          </van-button>
        </div>
      </template>
    </van-sku>

  </div>


</template>

<script>
  import {
    Toast,
    PullRefresh,
    Swipe,
    SwipeItem
  } from 'vant';
  export default {
    comments:{
      [PullRefresh.name]: PullRefresh,
      [Swipe.name]: Swipe,
      [SwipeItem.name]: SwipeItem
    },
    data() {
      return {
        //顶部选择栏
        categories: [
          {
            name: "魅焰红",
            type: 1
          },
          {
            name: "幻境紫",
            type: 2
          }
        ],
        //商品卡片列表,这些一般会在后台设置 一般包含id(类似于主键,序号),title(商品名称),price(商品价格),
        // desc(描述),tag(商品标签,如 Micro USB接口、内存3GB),tumb(商品图片)
        phones: [
          {
            name: "phones",
            type: 1
          },{
            name: "phones2",
            type: 2
          }
        ],
        //迭代显示标志位
        show: true,
        //库存及商品规格
        sku: {},
        //商品信息
        goods: {}
      }
    },
    created(){
      const _this = this
      axios.get('http://localhost:8181/phone/index').then(function (resp) {
        _this.phones = resp.data.data.phones
        _this.categories = resp.data.data.categories
      })
    },
    methods: {
      onClick(index) {
        const _this = this
        axios.get('http://localhost:8181/phone/findByCategoryType/'+this.categories[index].type).then(function (resp) {
          _this.phones = resp.data.data
        })
      },
      buy(index){
        this.show = true
        const _this = this
        axios.get('http://localhost:8181/phone/findSpecsByPhoneId/'+this.phones[index].id).then(function (resp) {
          _this.goods = resp.data.data.goods
          _this.sku = resp.data.data.sku
        })
      },
      onBuyClicked(item){
        this.$store.state.specsId = item.selectedSkuComb.s1
        this.$store.state.quantity = item.selectedNum
        this.$router.push('/addressList')
      }
    }
  }
</script>
