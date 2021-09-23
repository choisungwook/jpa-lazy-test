module.exports = {
  transpileDependencies: ["vuetify"],
  devServer: {
    proxy: {
      "/": {
        target: "https://jpaschool-backend.choicloudlab.com",
        pathRewrite: { "^/": "" },
        changeOrigin: true,
      },
    }
  }
};
