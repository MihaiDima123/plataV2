import {Flex, FormLabel, Input, InputProps} from "@chakra-ui/react";
import ControlledInput from "lib/form-component/ControlledInput.tsx";
import React from "react";

type FormInputProps = InputProps & {
    label?: string,
    name: string,
    inputComponent?: React.FC
}

const FormInput = ({name, label, inputComponent, ...rest}: FormInputProps) => {
    return (
        <Flex direction="column" width={'100%'}>
            <FormLabel>{label}</FormLabel>
            <ControlledInput
                Component={inputComponent || Input}
                name={name}
                {...rest}
            />
        </Flex>
    )
}

export default FormInput