import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import path from 'path'

export default defineConfig({
  plugins: [react()],
  server: {
    port: 3000,
  },
  resolve: {
    alias: {
      types: path.resolve(__dirname, 'src/types'),
      api: path.resolve(__dirname, 'src/api'),
      assets: path.resolve(__dirname, 'src/assets'),
      components: path.resolve(__dirname, 'src/components'),
      guards: path.resolve(__dirname, 'src/guards'),
      lib: path.resolve(__dirname, 'src/lib'),
      pages: path.resolve(__dirname, 'src/pages'),
      providers: path.resolve(__dirname, 'src/providers'),
      routes: path.resolve(__dirname, 'src/routes'),
    },
  },
})
