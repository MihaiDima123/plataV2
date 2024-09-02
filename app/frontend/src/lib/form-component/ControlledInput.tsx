import React from "react";
import {useFormContext} from "react-hook-form";

type ControlledInputProps = {
    Component: React.FC<any>,
    name: string,
    props?: any
}

const ControlledInput = ({name, Component, ...rest}: ControlledInputProps) => (
    <Component
        {...useFormContext().register(name)}
        {...rest}
    />
)

export default ControlledInput
