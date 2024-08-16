import MyChakraProvider from "providers/ChakraProvider";
import router from "routes/route.tsx";
import {RouterProvider} from "react-router-dom";
import './index.css'
import AuthContextProvider from "providers/AuthContextProvider.tsx";

function App() {
  return (
    <MyChakraProvider>
        <AuthContextProvider>
            <RouterProvider
                router={router}
            />
        </AuthContextProvider>
    </MyChakraProvider>
  )
}

export default App
