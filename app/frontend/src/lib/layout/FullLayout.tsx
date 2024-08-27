import {Flex} from "@chakra-ui/react";
import MainSidebar from "components/main-navigation/MainSidebar.tsx";
import {Outlet} from "react-router-dom";

const FullLayout = () => {
    return (
        <Flex
            height={'100vh'}
            width={'100vw'}
            alignItems={'flex-start'}
            justifyContent={'flex-start'}
        >
            <MainSidebar>
                <main>
                    <Outlet />
                </main>
            </MainSidebar>
        </Flex>
    )
}

export default FullLayout
