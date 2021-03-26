<template>
    <div>
        <van-address-edit
                :area-list="areaList"
                show-delete
                show-set-default
                show-search-result
                :area-columns-placeholder="['请选择', '请选择', '请选择']"
                @save="onSave"
                @delete="onDelete"
        />
    </div>
</template>

<script>
    import { Toast } from 'vant';
    import AreaList from '../api/area'

    export default {
        data() {
            return {
                areaList:AreaList,
            };
        },
        methods: {
            onSave() {
                let instance = Toast('添加成功');//弹窗
                setTimeout(()=>{
                    instance.close();//关闭弹窗
                    this.$router.push('/addressList');//this指vue项目,$指取地址,
                    // router是该项目中的router文件夹,里面的index.js设置了路由操作:Vue.use(VueRouter)
                },1000)//setTimeout设置了1000ms的时间,时间过后执行括号内的内容
            },
            onDelete() {
                history.go(-1)//往后退一页,退回到上一页
            }
        },
    };
</script>

<style scoped>

</style>
