//const { defineConfig } = require('@vue/cli-service')
//module.exports = defineConfig({
//transpileDependencies: true,
//lintOnSave:false
//})

module.exports = {
  publicPath: '/',
  lintOnSave: false,
  devServer: {
    host: '0.0.0.0',
    port: 6103,
    client: {
      webSocketURL: 'ws://0.0.0.0:6103'
    },
    headers: {
      'Access-Control-Allow-Origin': '*',
    },
  },
  transpileDependencies:true,
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

