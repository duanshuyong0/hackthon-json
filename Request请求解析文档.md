### Request 请求解析文档

### Request解析步骤

#### 1. 解析整体思路

深度遍历每个子节点，根据id获取对应的value，并将结构体添加到结果树中。
如遇子节点，将子节点添加到父节点的"sub"数组字段中，从而保留层级关系。

最后，将解析好的数据结构，填充到占位符${formValues}位置。
并生成对应id的onclick事件，点击后发送网络请求。

#### 2. 输入和输出

输入
```json
"request": {
    "submit": {
        "event": "${submitButton}:click",
        "action": "submit1.htm",
        "type": "POST",
        "fields": {
            "data": "${formValues}",
            "type": "public"
        }
    },
    "save": {
        "event": "${saveButton}:click",
        "action": "submit2.htm",
        "type": "POST",
        "fields": {
            "data": "${formValues}",
            "type": "save"
        }
    }
}
```

输出
```js
// 返回<id,function(onclick)>的Map
return {
    "submitButton" : function onclick(){
        request(url = "submit1.htm",
                type = "POST",
                body = {
                    "data": ${formValues}
                    "type": "public"      
                }       
        )
    },
    "saveButton" : function onclick(){
        request(url = "submit2.htm",
                type = "POST",
                body = {
                    "data": ${formValues}
                    "type": "save"           
                }       
        )
    }
}
```

#### 3. 生成${formValues}值

##### 最终目标结构

```json
[
    {
        "id": "resourceConfirmType",
        "value": 6,
        "sub": [
            {
                "id": "secondComfirm",
                "value": 2
            } 
        ]
    },
    {
        "id": "saleOther",
        "value": ""
    }
]
```

##### 生成步骤

以exp3为例
首先忽略结构树的其他节点，只关注"id"和"value"。
从根节点开始遍历，如果有"id"和"value"，则将结构体添加到结果树中。
继续执行深度遍历，并将子节点的结构体放入到父节点的"sub"数组中
```json
{
    {
        "id": "resourceConfirmType",
        "props": {
            "value": {
                "value": "secondComfirm",
                "secondComfirm": {
                    "comfirmTime": {
                        "value": 6
                    }
                }
            },
            "subItems": [
                {
                    "id": "immediatelyComfirm",
                },
                {
                    "id": "secondComfirm",
                    "subItems": [
                        {
                            "dataSource": [
                                {
                                    "value": 2
                                },
                                {
                                    "value": 6
                                }
                            ]
                        }
                    ]
                }
            ]
        }
    },
    {
        "id": "saleOther",
        "props": {
            "value": "",
        }
    },
    {
        "id": "saveButton",
    },
    {
        "id": "submitButton",
    }
}
```