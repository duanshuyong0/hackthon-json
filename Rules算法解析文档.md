## Rules 算法解析文档

### Rules解析算法

#### 1. 算法整体思路

通过解析Rules中的表达式，以id为最小粒度，为指定id生成对应的onchange方法。

当指定id的value发生变化时，将触发onchange方法，若满足表达式，Rules中定义的修改将会生效。

#### 2. 输入输出

输入
```json
"rules": [
        {
            "condition": "${resourceLocation.props.value.value} == 'abroad' && ${resourceLocation.props.value.abroad.abroadCountry.value} == '1111' ",
            "target": {
                "saleOther": {
                    "props": {
                        "visible": true
                    }
                }
            }
        },
        {
            "condition": "${resourceLocation.props.value.abroad.abroadCountry.value} !== '1111' ",
            "target": {
                "saleOther": {
                    "props": {
                        "visible": false
                    }
                }
            }
        }
    ]
```

输出
```js
// 根据id获取组件
export function getId(id){}
// 根据id更新组件的props属性
export function setById(props){}

// 返回<id,function(onchange)>的Map
return {
    "resourceLocation" : function onchange(){
        if (getId("resourceLocation").props.value.value == 'abroad') {
            if (getId("abroadCountry").value == '1111') {
                getId("saleOther").props.setById({
                    "visible": true
                })
            }
        }
    },
    "abroadCountry" : function onchange(){
        if (getId("abroadCountry").value == '1111') {
            if (resourceLocation.props.value.value == 'abroad') {
                 getId("saleOther").props.setById({
                    "visible": true
                 })
            }
        }
    
        if (abroadCountry.value !== '1111') {
            getId("saleOther").props.set({
                "visible": false
            })
        }
    }
}
```

#### 3. 算法过程

```js
// 从总表达式中获取每个id以及它关联的子表达式
function getIdFromExpression(conditions) {} 
// 生成id的onchange事件函数
function generateIdOnchangeFunc(IdConditionExpression,expressionTarget){}
// 组件id以及它对应的条件判断表达式集合
interface IdConditionExpression {
    id;
    expression[];
}
// 目标id以及它对应的props值表达式
interface IdTargetExpression {
    targetId;
    propsExpression;
}

// 解析rules，生成Map<id,function(onchange)>
function parse(rules){
    // 返回<id,function(onchange)>的Map
    Map<id, func> idFuncMap;

    // 遍历rules列表
    for (rule in rules) {
        // 获取条件表达式
        conditionExpression = rule.get("condition")
        // 获取满足表达式时的props更新值
        IdTargetExpression expressionTarget = rule.get("target").getItem().get("props")

        // 从表达式中获取指定的id，以及与它相关的子表达式
        IdConditionExpression[] ides = getIdFromExpression(conditionExpression)

        // 遍历每个指定的id
        for (idConditionExpression in ides) {
            // 为指定id，基于相关的子表达式，生成function函数
            // func：当满足所有的子表达式时，将目标属性修改为expressionTarget表达式的值
            func = generateIdOnchangeFunc(idConditionExpression, expressionTarget)
            
            // 将生成的function和id绑定放到Map中
            idFuncMap.put(idConditionExpression.id, func)
        }
    }

    return idFuncMap;
}
```

### Rules语法规则

#### 1. 使用${}表达式来指定目标值
```text
id1.id2.id3.props.value
```

#### 2. ${}表达式按照最右匹配原则，匹配最右一个不为"value"或"props"的值
```text
id1.id2.id3.props.value => 表示id3的属性值
```

#### 3. 支持表达式之间的与或关系
```text
${1} == 1 && ${2} == 2
${1} == 1 || ${2} == 2
```

#### 3. target表达式请按照四层结构
```json
"target": { ①第一层为target节点
  "${id}": { ②第二层为具体id节点
    "props": { ③第三层为props节点
      "1": "1", ④第四层为具体属性值节点
      "2": "2",
      "3": "3"
    } 
  }
}
```