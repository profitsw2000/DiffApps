package profitsw2000.diffapps.mappers

import profitsw2000.diffapps.entity.filmdetails.Person
import profitsw2000.diffapps.entity.topfilms.TopFilms
import profitsw2000.diffapps.model.filmdetails.PersonDTO
import profitsw2000.diffapps.model.topfilms.TopFilmsDTO

class PersonMapper (){
    fun map(personDTO: PersonDTO): Person {
        return Person(
            name = personDTO.name
        )
    }
}