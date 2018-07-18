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
事件处理
<div id="app">

  <br/>---------click1--------<br/>
  <button @click="count+=1">ADD</button>
  <p>count:{{count}}</p>

  <br/>---------click2--------<br/>
  <button @click="greet">greet</button>
  <br/>---------props 校验--------<br/>
  <val-component str="111"></val-component>
  <val-component v-bind:num="111" ></val-component>
</div>
<script>

  var valConpoment = {
    props: {
      str: String,
      num: Number
    },
    template: '<p>props校验，字符串{{str}},数值{{num}}</p>'
  };

  var vm = new Vue({
    el: '#app',
    data: {
      count: 0,
      name: 'lenji'
    },
    methods: {
      greet: function (event) {
        alert("hello " + this.name + " !" + event.tagName);
      }
    },
    components: {
      'val-component': valConpoment
    }
  });
</script>
</body>
</html>
