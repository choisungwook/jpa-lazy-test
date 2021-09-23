module.exports = {
  transpileDependencies: ["vuetify"],
  devServer: {
    proxy: {
      "/api": {
        target: "https://jpaschool-backend.choicloudlab.com",
        changeOrigin: true,
      },
    }
  }
};
