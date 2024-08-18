import i18next from 'i18next';
import {initReactI18next} from "react-i18next";
import enTranslation from '../locales/en/translation.json'
import roTranslation from '../locales/ro/translation.json'

i18next
.use(initReactI18next)
    .init({
        resources: {
            en: {
                translation: enTranslation
            },
            ro: {
                translation: roTranslation
            }
        },
        fallbackLng: "en",
    }).then(_r => true/*Do nothing*/);