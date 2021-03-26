前端对后端的API，请求由前端向后端发起,请求URL是后端的URL,参数是请求时的额外请求头,返回格式是后端返回的值,返回格式前端后端要约定好一致

# 1,首页数据

请求

`GET /phone/index`

参数

`无`

返回格式与举例

```json
{
  code: 0,//状态码
  msg: '成功',//连接提示信息
  //具体数据
  data:{
        //顶部选择栏,分类
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
        //分类对应的数据,商品卡片列表,这些一般会在后台设置 一般包含id(类似于主键,序号),title(商品名称),price(商品价格),
        // desc(描述),tag(商品标签,如 Micro USB接口、内存3GB),tumb(商品图片)
        phones: [
          {
          	id: 1,
          	title:"Honor 8A",
          	price: "2800.00.00",
          	desc: "魅焰红",
          	//商品标签
          	tag:[
                {
                	name:"720珍珠屏"
                },
                {
                	name:"Micro USB接口"
                }
          	],
          	//商品图片
          	thumb:"../static/e8a.jpg"
          }
        ],
        //迭代显示标志位
        show: true,
        //库存及商品规格
        sku: '',
        //商品信息
        goods: ''
      }
}
```



# 2,根据类型查手机

请求

`GET /phone/findByCategoryType`

参数

`CategoryType: 1`

返回格式与举例

```json
{
   code: 0,//状态码
   msg: '成功',//连接提示信息
   //具体数据
   data: [
      {
      	id: 1,
      	title:"Honor 8A",
      	price: "2800.00.00",
      	desc: "魅焰红",
      	//商品标签
      	tag:[
            {
            	name:"720珍珠屏"
            },
            {
            	name:"Micro USB接口"
            }
      	],
      	//商品图片
      	thumb:"../static/e8a.jpg"
      }
}
```


# 3,查询手机规格

请求

`GET  /phone/findSpecsByPhoneId`

参数

``` 
PhoneId: 1
```

返回格式与举例

```json
{
    code:0,
    msg:"成功",

    data: {
		goods:{
			pictures:'../static/e5e.jpg'
		},
		//库存及商品规格
		sku:{
			tree:[
				{
					k:'规格',
					v:[
						{
							id:1,
							name:'32GB',
							imgUrl:"../static/e22e.jpg",
							previewimgUrl:"../static/e22we.jpg",
						}
					],
					k_s:'s1'
				}
			]，
			list:[
			{
				s1:1,
				price:280000,
				//库存
				stock_num:1
			}
			],
			price:"2800.00",
			stock_num:2,
			//是否显示规格
			none_sku:false,
			//是否隐藏库存
			hide_stock:false
		}
    }
}
```

# 4,查询用户收货地址列表

`GET /address/list`

参数

`无`

返回格式与举例

```json
{
	code:0,
	msg:"成功",
	data:{
            areaCode: '330104',//注意数据类型
            id: '1',
            name: '张三',
            tel: '13000000000',
            address: '浙江省杭州市西湖区文三路 138 号东方通信大厦 7 楼 501 室',
            isDefault: true,
          }
}
```

# 5,新增用户地址

请求

`POST /address/create`

参数

```json
{
  name: '张三',
  tel: '13000000000',
  province:'北京市',
  city:'北京市',
  country:'东城区',
  areaCode: '110101',//注意数据类型
  postalCode:'',
  addressDetail: '138 号东方通信大厦 7 楼 501 室',
  isDefault: true,
}
```

返回格式与举例

```json
{
    code:0,
    msg:'成功',
    data:null
}
```

# 6,修改地址

请求

`POST /address/update`

参数

```json
{
  name: '张三',
  tel: '13000000000',
  province:'北京市',
  city:'北京市',
  country:'东城区',
  areaCode: '110101',//注意数据类型
  postalCode:'',
  addressDetail: '138 号东方通信大厦 7 楼 501 室',
  isDefault: true,
}
```

返回格式与举例

```json
{
    code:0,
    msg:'成功',
    data:null
}
```

# 7,创建订单

请求

`POST /order/create`

参数

```json
{
  name: '张三',
  tel: '13000000000',
  address: '浙江省杭州市西湖区文三路 138 号东方通信大厦 7 楼 501 室',
    //商品规格,不需要传商品id了,数据库中通过商品规格可以找到商品
  spesId:1,
    //商品数量
  quantity:1
}
```

返回格式与举例

```json
{
    code:0,
    msg:'成功',
    data:{
        //订单号
        orderId: "132378978942"
    }
}
```

# 8,订单详情

请求

`GET /order/detail`

参数

```json
orderId: "132378978942"//订单号
```
返回格式与举例

```json
{
    code:0,
    msg:'成功',
    data:{
    
    }
}
```
# 9,支付订单

请求

`POST /order/pay`

参数

`orderId: "132378978942"//订单号`

返回格式与举例

```json
{
    code:0,
    msg:'成功',
    data:orderId: "132378978942"//订单号
    
}
```
