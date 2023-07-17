package com.shushant.astroyoga.onboard.filldetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun getCarouselItems(
    viewModel: UserDetailsViewModel,
    userState: UserDetailsState,
    navigate: () -> Unit,
    action: () -> Unit,
): List<@Composable () -> Unit> {
    return mutableListOf<@Composable (() -> Unit)>().apply {
        add {
            UserNameItem(viewModel::setUserName, userState) {
                action.invoke()
            }
        }
        add {
            TellYourGenderItem(viewModel::setGender, userState) {
                action.invoke()
            }
        }
        add {
            TellYourSentimentalStatusItem(viewModel::setSentimentalStatus, userState) {
                action.invoke()
            }
        }
        add {
            SelectorDOBItem(viewModel::setDob, userState) {
                action.invoke()
            }
        }
        add {
            SelectorTOBItem(viewModel::setTob, userState) {
                action.invoke()
            }
        }
        add {
            SelectPOBItem(viewModel::setPob, userState) {
                action.invoke()
            }
        }

        add {
            PalmReadingItem(userState) {
                action.invoke()
            }
        }
        add {
            EnableReminderItem(userState) {
                navigate.invoke()
            }
        }
    }
}
