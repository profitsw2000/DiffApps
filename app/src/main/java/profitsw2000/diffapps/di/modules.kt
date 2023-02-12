package profitsw2000.diffapps.di

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.koin.dsl.module
import profitsw2000.diffapps.data.firestore.FirebaseRepository
import profitsw2000.diffapps.data.local.FakeRepository
import profitsw2000.diffapps.domain.Repository
import profitsw2000.diffapps.presentation.viewmodel.MainViewModel

val appModule = module {

    single { MainViewModel(get()) }
    single<Repository> { FirebaseRepository(get()) }
    single { Firebase.firestore }
}