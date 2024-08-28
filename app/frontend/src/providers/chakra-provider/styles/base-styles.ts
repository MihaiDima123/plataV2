const htmlStyle = {
    backgroundColor: "gray.100",
    color: "gray.800",
};
const baseGlobalStyle = {
    global: {
        "html": htmlStyle,
        body: {
            ...htmlStyle,
            display: "flex",
            alignItems: "center",
            justifyContent: "center",
            width: "100vw",
            height: "100vh",
        },
        main: {
            padding: '3',
            width: '100%',
            height: '100%'
        },
        a: {
            color: "primary.500",
            _hover: {
                textDecoration: "underline",
            },
        },
    },
}

export {
    baseGlobalStyle
}