package com.shushant.astroyoga.onboard.filldetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

import com.shushant.astroyoga.feature.onboard.R
import com.shushant.astroyoga.onboard.filldetails.pob.BottomSheetLayout
import com.shushant.astroyoga.onboard.filldetails.pob.PlaceOfBirthViewModel
import com.shushant.astroyoga.data.model.LocationSearchResultItem
import com.shushant.common.compose.theme.Typography
import com.shushant.common.compose.ui.AppLoader
import com.shushant.common.compose.ui.ChattiezButton
import com.shushant.resource.AppResource
import com.skydoves.whatif.whatIfNotNullOrEmpty
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SelectPOBItem(
    pob: (LocationSearchResultItem?) -> Unit,
    userState: UserDetailsState,
    placeOfBirthViewModel: PlaceOfBirthViewModel = getViewModel(),
    moveForward: () -> Unit
) {
    val placeState by placeOfBirthViewModel.state.collectAsStateWithLifecycle()
    val keyboardController = LocalSoftwareKeyboardController.current
    if (placeState.isLoading) {
        AppLoader()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(top = 30.dp)
    ) {
        Image(
            painter = painterResource(id = AppResource.TELL_YOUR_NAME.drawable),
            contentDescription = "",
            contentScale = ContentScale.Inside,
        )
        Text(
            text = stringResource(id = R.string.dob_title),
            color = Color.Gray,
            style = Typography.bodyLarge.copy(fontSize = 18.sp),
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp)
        )

        Text(
            text = stringResource(R.string.place_ofb),
            color = Color.Black,
            style = Typography.bodyLarge.copy(
                fontSize = 20.sp, fontWeight = FontWeight.ExtraBold, textAlign = TextAlign.Start
            ),
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        TextField(
            value = userState.pob?.getPlaceName ?: "",
            onValueChange = { value ->
                pob.invoke(LocationSearchResultItem(displayName = value))
            },
            textStyle = Typography.bodyLarge.copy(fontSize = 28.sp, color = Color.Black),
            keyboardOptions = KeyboardOptions(
                KeyboardCapitalization.Words,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(onSearch = {
                userState.pob?.getPlaceName?.let { placeOfBirthViewModel.fetchPlaces(it) }
                keyboardController?.hide()
            }),
            placeholder = {
                Text(
                    text = stringResource(R.string.city), style = Typography.bodyLarge.copy(
                        fontSize = 28.sp, color = Color.LightGray, textAlign = TextAlign.Start
                    )
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                cursorColor = Color.Black
            ),
            singleLine = true
        )
        placeState.data?.whatIfNotNullOrEmpty { data ->
            BottomSheetLayout(data) {
                pob.invoke(it).also {
                    placeOfBirthViewModel.clearData()
                }
            }
        }
        ChattiezButton(
            buttonText = stringResource(R.string.continue_text),
            enabled = userState.pob?.lat.isNullOrEmpty().not()
        ) {
            moveForward.invoke()
        }
    }
}