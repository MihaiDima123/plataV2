import {useForm} from "react-hook-form";
import {useTranslation} from "react-i18next";
import FormInput from "lib/form-component/FormInput.tsx";
import FormComponent from "lib/form-component/FormComponent.tsx";
import PlataButton from "components/button/PlataButton.tsx";
import PlataBox from "components/box/PlataBox.tsx";
import {Textarea} from "@chakra-ui/react";

type CreateGuildFormType = {
    name?: string,
    description?: string,
}

const CreateGuild = () => {
    const { t } = useTranslation()
    const formContext = useForm<CreateGuildFormType>()

    const submitHandler = (_values: CreateGuildFormType) => {
        // TODO
    }

    return (
        <PlataBox>
            <FormComponent formContext={formContext} >
                <FormInput
                    name={'name'}
                    label={t('form.labels.name')}
                />
                <FormInput
                    name={'description'}
                    label={t('form.labels.description')}
                    inputComponent={Textarea}
                />
                <PlataButton
                    onClick={formContext.handleSubmit(submitHandler)}
                >
                    {t('form.actions.create')}
                </PlataButton>
            </FormComponent>
        </PlataBox>
    )
}

export default CreateGuild