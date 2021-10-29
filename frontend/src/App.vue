<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<style>
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap%27");

* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
  -webkit-tap-highlight-color: transparent;
}

body {
  display: flex;
  height: 100vh;
  flex-direction: column;
  align-items: center;
  background-color: lightgray;
  color: hsl(193, 9%, 19%);
  margin: 0;
  font-family: "Noto Sans KR", sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

#app{
  width: 500px;
  background-color: white;
}

button {
  border: none;
  background-color: #fff;
}

a {
  color: inherit;
  text-decoration: none;
}

ol,
ul,
li {
  list-style-type: none;
  padding-left: 0;
  margin-left: 0;
}

button,
input,
textarea {
  font-family: "Noto Sans KR", sans-serif;
}

code {
  font-family: source-code-pro, Menlo, Monaco, Consolas, "Courier New",
    monospace;
}

/* #app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
} */
</style>
