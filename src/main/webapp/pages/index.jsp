<%--
  Created by IntelliJ IDEA.
  User: linjian
  Date: 2018/2/26
  Time: 10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
  <script src="https://unpkg.com/vue/dist/vue.js"></script>

</head>
<body>
Hello World!
<div id="app">
  ----------文本显示-------
  <p>{{ message }}</p>
  ----------输入框 -------
  <br>
  <input v-model="inputT" placeholder="edit me"/>
  <br>
  ----------多行输入框 -------
  <br>
  <textarea v-model="inputT" placeholder="edit me"></textarea>
  <br>
  <p>输入框的value:{{ inputT }}</p>
  ----------单选框 -------
  <br>
  <input type="radio" id="one" value="One" v-model="singleRadio">
  <label for="one">One</label>
  <br>
  <input type="radio" id="two" value="Two" v-model="singleRadio">
  <label for="two">Two</label>
  <br>
  <span>Picked: {{ singleRadio }}</span>
  <br>
  ----------复选框 -------
  <br>
  <input type="checkbox" id="checked1" value="checked1" v-model="checked">
  <label for="checked1">checked1</label>
  <input type="checkbox" id="checked2" value="checked2" v-model="checked">
  <label for="checked2">checked2</label>
  <input type="checkbox" id="checked3" value="checked3" v-model="checked">
  <label for="checked3">checked3</label>
  <br>
  <span>Checked: {{ checked }}</span>
  <br>----------选择框 -------<br>
  <select v-model="selected">
    <option disabled value="">请选择</option>
    <option value="valueA">A</option>
    <option value="valueB">B</option>
    <option value="valueC">C</option>
  </select>
  <span>Selected: {{ selected }}</span>
  <br>----------绑定变量v-bind -------<br>
  <input type="radio" v-model="blind" v-bind:value="a">
  <span>一开始没有值，选中后出现变量a的值: {{ blind }}</span>
  <br>----------更新一次v-once -------<br>
  <span>这个将不会改变: {{ inputT }}</span>
  <br>----------解释成html v-html -------<br>
  <p> {{ htmlText }}</p>
  <p v-html="htmlText"></p>
  <br>----------watch -------<br>
  <p>{{ fullName() }}</p>
  <br>----------v-show和v-if对比 -------<br>
  <p v-if="!'if'">if
  <p>
  <p v-show="!'show'">show
  <p>
    <br>----------v-if需要有多个元素，用Template -------<br>
    <Template v-if="1">
  <p>1</p>
  <p>2</p>
  </Template>
  <br>----------全局Component -------<br>
  <global-component ctext="<global-component>"></global-component>
  <br>----------局部Component -------<br>
  <local-component></local-component>
  <br>----------复杂的to to list组件-------<br>
  <input v-model="toDoText"
         v-on:keyup.enter="addNewToDo"/>
  <ul>
    <li is="my-li"
        v-for="(term,index) in toDoList"
        v-bind:title="term.text"
        v-on:remove="toDoList.splice(index,1)"></li>
  </ul>
  <br>----------v-on-------<br>
  <button v-on:click="alert(1)">alert</button>
</div>
<script>
  Vue.component('global-component', {
    props: ['ctext'],
    template: '<p>这个是全局{{ctext}}</p>'
  });

  var localComponent = {
    template: '<p>这个是局部&lt;local-component\&gt;</p>'
  };


  Vue.component('my-li', {
    props: ['title'],
    template: '<li>{{title}}<button v-on:click="$emit(\'remove\')">X</button></li>'
  });

  var vm = new Vue({
    el: '#app',
    data: {
      message: '文本框!',
      inputT: '输入框',
      singleRadio: '',
      checked: [],
      selected: '',
      blind: '',
      a: true,
      htmlText: '<span style="color:red">it is red</span>',
      firstName: 'Foo',
      lastName: 'Bar',
      fullConponent: '这个是<global-component>',
      toDoText: '任务X',
      toDoList: [{text: '任务1'}, {text: '任务2'}, {text: '任务3'}]
    },
    methods: {
      fullName: function () {
        return this.firstName + ' ' + this.lastName + 123
      },
      addNewToDo: function () {
        this.toDoList.push({text:this.toDoText});
        this.toDoText='';
      }
    },
    components: {
      'local-component': localComponent
    }
  });
</script>
</body>
</html>
