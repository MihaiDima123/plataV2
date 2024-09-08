import {useForm} from "react-hook-form";
import {useTranslation} from "react-i18next";
import FormInput from "lib/form-component/FormInput.tsx";
import FormComponent from "lib/form-component/FormComponent.tsx";
import PlataButton from "components/button/PlataButton.tsx";
import PlataBox from "components/box/PlataBox.tsx";
import {Textarea, useToast} from "@chakra-ui/react";
import {useMutation} from "@tanstack/react-query";
import {CreateGuildDTO} from "api/guild/types/guild-types.ts";
import GuildService from "api/guild/GuildService.ts";
import {AxiosError} from "axios";
import {isEmpty} from "lodash";
import {useNavigate} from "react-router-dom";
import {GUILD_GUILD} from "routes/guarded-routes.tsx";

type CreateGuildFormType = {
    name?: string,
    description?: string,
}

const CreateGuild = () => {
    const { t } = useTranslation()
    const navigate = useNavigate()

    const formContext = useForm<CreateGuildFormType>()
    const toast = useToast()

    const createGuildHandle = useMutation({
        mutationFn: (request: CreateGuildDTO) => GuildService.createGuild(request),
        onSuccess: (id) => {
            toast({
                title: t('general.success'),
                status: 'success'
            })

            navigate(GUILD_GUILD.replace(":id", String(id)))
        },
        onError: (error: AxiosError<any>) => {
            toast({
                title: error.response?.data.message,
                status: 'error'
            })
        }
    })

    const submitHandler = (values: CreateGuildFormType) => {
        if (isEmpty(values.name)) {
            return
        }

        createGuildHandle.mutate({
            name: values.name!!,
            description: values.description
        })
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
                <PlataButton onClick={formContext.handleSubmit(submitHandler)} >
                    {t('form.actions.create')}
                </PlataButton>
            </FormComponent>
        </PlataBox>
    )
}

export default CreateGuild