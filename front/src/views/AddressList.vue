<template>
  <div>
    <van-address-list
            v-model="chosenAddressId"
            :list="list"
            default-tag-text="默认"
            @add="onAdd"
            @edit="onEdit"
            @select="onselect"
    />
  </div>
</template>

<script>
  import {Toast} from 'vant'
  export default {
    data() {
      return {
        chosenAddressId: 0,
        list: []
      };
    },
    created(){
      const _this = this
      axios.get('http://localhost:8181/address/list').then(function (resp) {
        _this.list = resp.data.data
      })
    },
    methods: {
      onAdd() {
        this.$router.push('/addressNew');
      },

      onEdit(item) {
        let data=JSON.stringify(item);//将JSON格式的item转换成字符串形式,因为两个页面之间传值只能传字符串,页面传值只能传文本信息,不能传对象
        this.$router.push({path:'/addressEdit',query:{item:data}})
      },
      onselect(item){
          // let instance = Toast('下单成功');
          // let data = JSON.stringify(item);
          // setTimeout(()=>{
          //   instance.close();
          //   this.$router.push({path:'/orderDetail',query:{item:data}})
          // },1000)

        let orderForm = {
          name: item.name,
          tel: item.tel,
          address: item.address,
          specsId: this.$store.state.specsId,
          quantity: this.$store.state.quantity
        }
        const _this = this
        axios.post('http://localhost:8181/order/create',orderForm).then(function (resp) {
          if(resp.data.code == 0){
            let instance = Toast('下单成功');
            setTimeout(() => {
              instance.close();
              _this.$router.push('/OrderDetail?orderId='+resp.data.data.orderId)
            }, 1000)
          }
        })
      }
    },
  };
</script>
