import {useParams} from "react-router-dom";

const Guild = () => {
    const {id} = useParams()

    return (
        <div>
            Guild {id}
        </div>
    )
}

export default Guild
