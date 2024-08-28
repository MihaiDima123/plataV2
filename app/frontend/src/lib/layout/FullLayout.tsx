import {Flex} from "@chakra-ui/react";
import {Outlet} from "react-router-dom";
import SidebarContent from "components/main-navigation/SidebarComponent.tsx";

const FULL_PAGE_LAYOUT_WIDTH = '1200px'

const FullLayout = () => {
    return (
        <Flex
            width={FULL_PAGE_LAYOUT_WIDTH}
            height={'100vh'}
            maxWidth={'100vw'}
            alignItems={'flex-start'}
            justifyContent={'center'}
        >
            <SidebarContent />
            <main style={{ width: '100%' }}>
                <Outlet/>
            </main>
        </Flex>
    )
}

export default FullLayout
