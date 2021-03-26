<template>
<div>
    <van-image
        width="100"
        height="100"
        src="../static/paysuccess.png"/>
    <van-cell :title="amount"/>
    <van-cell-group>
        <van-cell >
            <van-col span="21">配送方式</van-col>
            <van-col>快递</van-col>
        </van-cell>
    </van-cell-group>
    <van-cell-group>
        <van-button type="primary" size="large"
        @click="onSubmit"
        >
            订单详情
        </van-button>
    </van-cell-group>
</div>
</template>

<script>
    export default {
        name: "PaySuccess",
        created() {
            this.orderId=this.$route.query.orderId;
            this.amount=this.$route.query.amount;
        },
        data(){
            return{
                amount:null,
                orderId:null
            }
        },
        methods:{
            onSubmit:function () {
                const _this=this;
                _this.$router.push('/orderInfo?orderId='+_this.orderId)
            }
        }
    }
</script>

<style scoped>

</style>
