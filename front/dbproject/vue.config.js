//const { defineConfig } = require('@vue/cli-service')
//module.exports = defineConfig({
  //transpileDependencies: true,
  //lintOnSave:false
//})

module.exports = {
  publicPath: '/',
  lintOnSave: false,
  devServer: {
    open: true,
    port: 8080,
    proxy: {
      '/api': {
        // 连接到后端的路径
        target: 'http://localhost:7078',
        changeOrigin: true,
        secure: false,
      }
    }
  }
}
