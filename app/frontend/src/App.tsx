import MyChakraProvider from "providers/chakra-provider/ChakraProvider.tsx";
import router from "routes/route.tsx";
import {RouterProvider} from "react-router-dom";
import AuthContextProvider from "providers/AuthContextProvider.tsx";
import ReactQueryProvider from "providers/ReactQueryProvider.tsx";
import './index.css'
import './i18n/init'

function App() {
  return (
    <MyChakraProvider>
        <ReactQueryProvider>
            <AuthContextProvider>
                <RouterProvider
                    router={router}
                />
            </AuthContextProvider>
        </ReactQueryProvider>
    </MyChakraProvider>
  )
}

export default App
