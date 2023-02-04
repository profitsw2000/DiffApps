package profitsw2000.diffapps.mappers

import profitsw2000.diffapps.entity.DataDTO
import profitsw2000.diffapps.model.Data

class DataMapper (private val childrenMapper: ChildrenMapper) {
    fun map(dataDTO: DataDTO): Data {
        return Data(
            after = dataDTO.after,
            before = dataDTO.before,
            children = dataDTO.children.map { childrenMapper.map(it) }
        )
    }
}