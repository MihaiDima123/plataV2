import {QueryClient, QueryClientProvider} from "@tanstack/react-query";
import React from "react";

const queryClient = new QueryClient();

type ReactQueryProviderProps = {
    children: React.ReactNode;
}

const ReactQueryProvider = (props: ReactQueryProviderProps) => {
    return (
        <QueryClientProvider client={queryClient}>
            {props.children}
        </QueryClientProvider>
    )
}

export default ReactQueryProvider