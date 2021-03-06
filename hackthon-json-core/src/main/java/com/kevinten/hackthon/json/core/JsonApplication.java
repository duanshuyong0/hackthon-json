package com.kevinten.hackthon.json.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class JsonApplication {

    public static void main(String[] args) {
        JSONObject jsonObject = JSON.parseObject(TEST_3);
        System.out.println(jsonObject);

    }

    private static String TEST_3 = """
                    {
                        "components": [
                            {
                                "id": "resourceConfirmType",
                                "uiType": "uiRadioGroup",
                                "props": {
                                    "label": "资源确认方式",
                                    "required": true,
                                    "value": {
                                        "value": "secondComfirm",
                                        "secondComfirm": {
                                            "comfirmTime": {
                                                "text": "6小时确认",
                                                "value": 6
                                            }
                                        }
                                    },
                                    "subItems": [
                                        {
                                            "id": "immediatelyComfirm",
                                            "uiType": "uiRadioGroup",
                                            "label": "即时确认"
                                        },
                                        {
                                            "id": "secondComfirm",
                                            "uiType": "uiRadioGroup",
                                            "label": "二次确认",
                                            "subItems": [
                                                {
                                                    "name": "comfirmTime",
                                                    "uiType": "uiRadio",
                                                    "label": "资源确认时长",
                                                    "required": true,
                                                    "dataSource": [
                                                        {
                                                            "text": "2小时确认",
                                                            "value": 2
                                                        },
                                                        {
                                                            "text": "6小时确认",
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
                                "id": "resourceLocation",
                                "uiType": "uiRadioGroup",
                                "required": true,
                                "props": {
                                    "label": "资源所在地",
                                    "value": {
                                        "value": "internal",
                                        "internal": {
                                            "value": "internalCity",
                                            "internalCity": {
                                                "value": "1-100001"
                                            }
                                        }
                                    },
                                    "subItems": [
                                        {
                                            "id": "internal",
                                            "uiType": "uiRadioGroup",
                                            "label": "国内(含港澳台)",
                                            "subItems": [
                                                {
                                                    "id": "internalCity",
                                                    "uiType": "uiSelect",
                                                    "children": [
                                                        {
                                                            "value": 1,
                                                            "text": "北京",
                                                            "children": [
                                                                {
                                                                    "value": 100001,
                                                                    "text": "北京"
                                                                }
                                                            ]
                                                        },
                                                        {
                                                            "value": 2,
                                                            "text": "上海",
                                                            "children": [
                                                                {
                                                                    "value": 200001,
                                                                    "text": "上海"
                                                                }
                                                            ]
                                                        },
                                                        {
                                                            "value": 4,
                                                            "text": "河南",
                                                            "children": [
                                                                {
                                                                    "value": 400001,
                                                                    "text": "郑州"
                                                                },
                                                                {
                                                                    "value": 400002,
                                                                    "text": "开封"
                                                                },
                                                                {
                                                                    "value": 400003,
                                                                    "text": "洛阳"
                                                                }
                                                            ]
                                                        }
                                                    ]
                                                }
                                            ]
                                        },
                                        {
                                            "id": "abroad",
                                            "uiType": "uiRadioGroup",
                                            "label": "其他国家/地区",
                                            "subItems": [
                                                {
                                                    "id": "abroadCountry",
                                                    "uiType": "uiSelect",
                                                    "children": [
                                                        {
                                                            "value": 1111,
                                                            "text": "泰国"
                                                        },
                                                        {
                                                            "value": 1112,
                                                            "text": "新加坡"
                                                        },
                                                        {
                                                            "value": 1113,
                                                            "text": "印度尼西亚"
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
                                "uiType": "uiInput",
                                "props": {
                                    "label": "是否搭售其他产品：",
                                    "required": false,
                                    "visible": false,
                                    "value": "",
                                    "placeholder": "填写搭售的产品名称（如：大象演出）"
                                }
                            },
                            {
                                "id": "saveButton",
                                "uiType": "uiButton",
                                "props": {
                                    "text": "保存草稿"
                                }
                            },
                            {
                                "id": "submitButton",
                                "uiType": "uiButton",
                                "props": {
                                    "text": "提交发布"
                                }
                            }
                        ],
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
                        ],
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
                    }
            """;
}
