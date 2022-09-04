package com.example.officedirectory.view.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.officedirectory.data.model.Person
import com.example.officedirectory.domain.GetContactUseCase
import com.example.officedirectory.view.uistate.ContactItemUIState
import com.example.officedirectory.view.uistate.ContactListUIState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
@ExperimentalCoroutinesApi
class ContactListViewModelTest {
    @get:Rule
    var rule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: ContactListViewModel
    private val getContactUsecase = mockk<GetContactUseCase>()
    private val contactResponse : List<Person> = listOf(
        Person("1","2022-01-24T20:52:50.765Z","https://randomuser.me/api/portraits/women/21.jpg","Future Functionality Strategist","pink","Crystel.Nicolas61@hotmail.com","Maggie","Brekke")
        )


    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        coEvery { getContactUsecase.invoke() } returns contactResponse
        viewModel = ContactListViewModel(getContactUsecase)
    }

    @Test
    fun `Load method correctly creates the ViewState`() = runTest {
        val values = mutableListOf<ContactListUIState>()
        viewModel.viewState.observeForever{
            values.add(it)
        }
        viewModel.fetchContacts()
        testDispatcher.scheduler.advanceUntilIdle()
        assert(values[0] is ContactListUIState.Loading)
        assert(values[1] ==
                ContactListUIState.Content(listOf(
                        ContactItemUIState("1","2022-01-24T20:52:50.765Z","https://randomuser.me/api/portraits/women/21.jpg","Future Functionality Strategist","pink","Crystel.Nicolas61@hotmail.com","Maggie","Brekke")
                )))

    }
}