import {FormProvider, UseFormReturn} from "react-hook-form";
import {Flex, FlexProps} from "@chakra-ui/react";

type FormInputProps = {
    children: any,
    formContext: UseFormReturn<any>,
    wrapperProps?: FlexProps
}

const FormComponent = (props: FormInputProps) => {
    return (
        <Flex
            width={'100%'}
            direction={'column'}
            gap={2}
            {...props.wrapperProps}
        >
            <FormProvider {...props.formContext} {...props.wrapperProps}>
                {props.children}
            </FormProvider>
        </Flex>
    )
}

export default FormComponent