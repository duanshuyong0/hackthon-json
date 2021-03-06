### 基于JSON配置文件动态生成具有联动关系的表单界面

#### 背景：

各中后台系统存在大量表单提交类页面，开发人员需结合具体业务场景将各表单项梳理并在View层手写代码实现，存在大量重复性开发。虽然各系统中有通过类似json配置表动态生成表单项的实践，但多数方案局限性较大，主要表现为各表单项之间复杂联动关系无法实现或联动性有限。

#### 需解决的问题：

基于某种表达表单项联动关系的JSON配置表（可参考附件或自行设计数据结构）， 开发一套可动态生成的、具有联动关系的表单界面。通过读取配置自动生成表单界面，降低开发人员重复开发成本。

#### 题目：

见场景 1、2、3

#### 产出物：

根据条件动态生成具有联动关系的表单页面前端代码