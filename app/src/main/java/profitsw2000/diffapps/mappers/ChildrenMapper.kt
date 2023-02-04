package profitsw2000.diffapps.mappers

import profitsw2000.diffapps.entity.ChildrenDTO
import profitsw2000.diffapps.model.Children

class ChildrenMapper (private val postMapper: PostMapper) {
    fun map(childrenDTO: ChildrenDTO): Children {
        return Children(
            post = postMapper.map(childrenDTO.postDTO)
        )
    }
}