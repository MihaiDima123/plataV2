import MyChakraProvider from "./providers/ChakraProvider.tsx";
import router from "./routes/route.tsx";
import {RouterProvider} from "react-router-dom";
import './index.css'

function App() {
  return (
    <MyChakraProvider>
      <RouterProvider
          router={router}
      />
    </MyChakraProvider>
  )
}

export default App
