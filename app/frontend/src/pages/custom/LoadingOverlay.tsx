import {Spinner} from "@chakra-ui/react";
import PageLayout from "lib/layout/PageLayout.tsx";

const LoadingOverlay = () => {

    return (
        <PageLayout>
            <Spinner height={20} width={20} />
        </PageLayout>
    )
}

export default LoadingOverlay