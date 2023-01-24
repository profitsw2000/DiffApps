package profitsw2000.diffapps.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import profitsw2000.diffapps.domain.Repository

class RepositoryImpl : Repository {
    private val coroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    override val carDriver: CarDriver = CarDriver(coroutineScope)
}