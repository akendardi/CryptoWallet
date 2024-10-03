package com.akendardi.cryptowallet.presentation.auth.auth_screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.akendardi.cryptowallet.R
import com.akendardi.cryptowallet.domain.states.auth.AuthResult
import com.akendardi.cryptowallet.presentation.auth.auth_screen.auth_screen_ui.button.AuthButton
import kotlinx.coroutines.delay

@Composable
fun AuthScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    goToMainScreen: () -> Unit,
    goToHelloScreen: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold { paddingValues ->
        Box(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 30.dp)
            )
            AuthScreenContent(
                authType = state.authType,
                textFieldsErrorsState = state.textFieldsErrorsState,
                textFieldsState = state.textFieldsState,
                onAuthTypeChanged = viewModel::changeAuthType,
                onEmailChanged = viewModel::onEmail,
                onNameChanged = viewModel::onUsername,
                onPasswordChanged = viewModel::onPassword,
                onButtonClick = {
                    viewModel.sendAuthRequest()
                    keyboardController?.hide()
                },
                onTextFieldIconClick = {
                    viewModel.actionTextField(it)
                }
            )

            HandleAuthResult(
                authType = state.authType,
                authResult = state.authResult,
                snackbarHostState = snackbarHostState,
                goToMainScreen = {
                    goToMainScreen()
                },
                goToHelloScreen = {
                    goToHelloScreen()
                },
                changeAuthType = viewModel::changeAuthType
            )
        }
    }


}

@Composable
fun HandleAuthResult(
    authType: AuthType,
    authResult: AuthResult,
    snackbarHostState: SnackbarHostState,
    goToMainScreen: () -> Unit,
    goToHelloScreen: () -> Unit,
    changeAuthType: (AuthType) -> Unit
) {
    when (authResult) {
        is AuthResult.Error -> {
            ShowSnackbarMessage(
                snackbarHostState,
                message = authResult.e,
                onAuthTypeChanged = { changeAuthType(AuthType.SIGN_IN) }
            )
        }

        AuthResult.Loading -> {
            Loading()
        }

        AuthResult.Success -> {
            LaunchedEffect(Unit) {
                if (authType == AuthType.SIGN_IN) {
                    goToMainScreen()
                } else {
                    goToHelloScreen()
                }
            }
        }

        AuthResult.SuccessSentLink -> {
            ShowSnackbarMessage(
                snackbarHostState = snackbarHostState,
                message = "Сообщение отправлено на вашу почту",
                onAuthTypeChanged = { changeAuthType(AuthType.SIGN_IN) }
            )
        }

        else -> Unit
    }
}

@Composable
fun Loading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background.copy(alpha = 0.5f))
            .clickable(enabled = false) { },
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ChangeAuthAndReset(
    authType: AuthType,
    onAuthTypeChanged: (AuthType) -> Unit
) {

    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        when (authType) {
            AuthType.SIGN_IN -> {


                ChangeAuthTypeText(
                    authType = AuthType.SIGN_UP,
                    textAlign = TextAlign.Start,
                    onAuthTypeChanged = { onAuthTypeChanged(AuthType.SIGN_UP) }
                )

                ChangeAuthTypeText(
                    authType = AuthType.RESET_PASSWORD,
                    textAlign = TextAlign.End,
                    onAuthTypeChanged = { onAuthTypeChanged(AuthType.RESET_PASSWORD) }
                )


            }

            AuthType.SIGN_UP -> {
                ChangeAuthTypeText(
                    authType = AuthType.SIGN_IN,
                    textAlign = TextAlign.Center,
                    onAuthTypeChanged = { onAuthTypeChanged(AuthType.SIGN_IN) }
                )
            }

            AuthType.RESET_PASSWORD -> {
                ChangeAuthTypeText(
                    authType = AuthType.SIGN_IN,
                    textAlign = TextAlign.Center,
                    onAuthTypeChanged = { onAuthTypeChanged(AuthType.SIGN_IN) }
                )
            }
        }


    }
}

@Composable
private fun ChangeAuthTypeText(
    authType: AuthType,
    textAlign: TextAlign,
    onAuthTypeChanged: (AuthType) -> Unit
) {
    val label = when (authType) {
        AuthType.SIGN_IN -> LocalContext.current.getString(R.string.sign_in)
        AuthType.SIGN_UP -> LocalContext.current.getString(R.string.sign_up)
        AuthType.RESET_PASSWORD -> LocalContext.current.getString(R.string.reset_password)
    }
    Text(
        modifier = Modifier
            .clickable {
                onAuthTypeChanged(AuthType.SIGN_IN)
            },
        text = label,
        color = MaterialTheme.colorScheme.secondary,
        textAlign = textAlign,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.titleSmall
    )
}


@Composable
fun AuthScreenContent(
    authType: AuthType,
    textFieldsState: TextFieldsState,
    textFieldsErrorsState: TextFieldsErrorsState,
    onAuthTypeChanged: (AuthType) -> Unit,
    onNameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onButtonClick: () -> Unit,
    onTextFieldIconClick: (FieldType) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
            .verticalScroll(rememberScrollState())
    )
    {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(20.dp))
            Title()
            AuthCard(
                authType,
                textFieldsState = textFieldsState,
                textFieldsErrorsState = textFieldsErrorsState,
                onAuthTypeChanged = {
                    onAuthTypeChanged(it)
                },
                onNameChanged = {
                    onNameChanged(it)
                },
                onEmailChanged = {
                    onEmailChanged(it)
                },
                onPasswordChanged = {
                    onPasswordChanged(it)
                },
                onButtonClick = {
                    onButtonClick()
                },
                onTextFieldIconClick = onTextFieldIconClick
            )
        }

    }


}

@Composable
private fun ShowSnackbarMessage(
    snackbarHostState: SnackbarHostState,
    message: String,
    onAuthTypeChanged: (AuthType) -> Unit
) {
    LaunchedEffect(Unit) {
        onAuthTypeChanged(AuthType.SIGN_IN)
        snackbarHostState.showSnackbar(
            message = message,
            actionLabel = "Понятно",
            duration = SnackbarDuration.Short
        )
    }
}


@Composable
fun Title() {

    var isCryptoAnimated by remember { mutableStateOf(false) }
    var isWalletAnimated by remember { mutableStateOf(false) }

    LaunchedEffect(isCryptoAnimated) {
        isCryptoAnimated = true
        delay(200)
        isWalletAnimated = true
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
    ) {

        AnimatedVisibility(
            visible = isCryptoAnimated,
            enter = slideInHorizontally() + expandHorizontally(expandFrom = Alignment.End) + fadeIn()
        ) {
            Text(
                text = "Crypto",
                textAlign = TextAlign.Start,
                fontSize = 60.sp,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(
                        PaddingValues(
                            top = 16.dp,
                            start = 60.dp
                        )
                    ),
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        AnimatedVisibility(
            visible = isWalletAnimated,
            enter = slideInHorizontally { fullWidth -> fullWidth } + fadeIn()
        ) {
            Text(
                text = "wallet",
                textAlign = TextAlign.Start,
                fontSize = 60.sp,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 120.dp
                    ),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }


}

@Composable
fun PasswordTextField(
    value: String,
    error: String,
    isPasswordVisible: Boolean = false,
    onValueChanged: (String) -> Unit,
    onPasswordIconClick: () -> Unit
) {
    val label = "Пароль"

    OutlinedTextField(
        value = value,
        onValueChange = { onValueChanged(it) },
        label = { Text(text = label) },

        singleLine = true,
        isError = error.isNotEmpty(),
        supportingText = {
            if (error.isNotEmpty()) {
                Text(text = error)
            }
        },
        trailingIcon = {
            IconButton(onClick = { onPasswordIconClick() }) {
                Icon(
                    imageVector = Icons.Default.RemoveRedEye,
                    contentDescription = null
                )
            }
        },
        visualTransformation = if (!isPasswordVisible) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password
        )
    )
}

@Composable
fun AuthTextField(
    fieldType: FieldType,
    value: String,
    error: String,
    onValueChanged: (String) -> Unit,
    onTextFieldIconClick: (FieldType) -> Unit
) {
    val label = if (fieldType == FieldType.USERNAME) {
        "Имя пользователя"
    } else {
        "Email"
    }


    OutlinedTextField(
        value = value,
        onValueChange = { onValueChanged(it) },
        label = { Text(text = label) },

        singleLine = true,
        isError = error.isNotEmpty(),
        supportingText = {
            if (error.isNotEmpty()) {
                Text(text = error)
            }
        },
        trailingIcon = {
            IconButton(onClick = { onTextFieldIconClick(fieldType) }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = null
                )
            }
        },
        keyboardOptions = if (fieldType == FieldType.USERNAME) {
            KeyboardOptions(
                keyboardType = KeyboardType.Text
            )
        } else {
            KeyboardOptions(
                keyboardType = KeyboardType.Email
            )

        }
    )
}

@Composable
fun CardFirstText(authType: AuthType) {
    val context = LocalContext.current

    var isTextVisible by remember { mutableStateOf(true) }
    val targetLabel = when (authType) {
        AuthType.SIGN_IN -> context.getString(R.string.sign_in)
        AuthType.SIGN_UP -> context.getString(R.string.registration)
        AuthType.RESET_PASSWORD -> context.getString(R.string.reset_password_title)
    }

    var currentLabel by remember { mutableStateOf(targetLabel) }

    LaunchedEffect(authType) {
        isTextVisible = false

        delay(100)

        currentLabel = targetLabel

        isTextVisible = true
    }
    val textAlpha by animateFloatAsState(
        targetValue = if (isTextVisible) 1f else 0f,
        animationSpec = tween(durationMillis = 100),
        label = ""
    )
    Text(
        text = currentLabel,
        modifier = Modifier.alpha(textAlpha),
        style = MaterialTheme.typography.titleLarge
    )
}

@Composable
private fun AnimatedPasswordTextField(
    authType: AuthType,
    value: String,
    error: String,
    isPasswordVisible: Boolean = false,
    onValueChanged: (String) -> Unit,
    onTextFieldIconClick: (FieldType) -> Unit
) {
    val visible = authType == AuthType.SIGN_IN || authType == AuthType.SIGN_UP
    AnimatedVisibility(
        visible = visible,
        enter = expandVertically(animationSpec = tween(500)) + fadeIn(),
        exit = shrinkVertically(animationSpec = tween(500)) + fadeOut()
    ) {
        PasswordTextField(
            value = value,
            onValueChanged = {
                onValueChanged(it)
            },
            isPasswordVisible = isPasswordVisible,
            error = error,
            onPasswordIconClick = {
                onTextFieldIconClick(FieldType.PASSWORD)
            }
        )
    }
}

@Composable
private fun AnimatedNickNameTextField(
    authType: AuthType,
    value: String,
    error: String,
    onValueChanged: (String) -> Unit,
    onTextFieldIconClick: (FieldType) -> Unit
) {
    val visible = authType == AuthType.SIGN_UP
    AnimatedVisibility(
        visible = visible,
        enter = expandVertically(animationSpec = tween(500)) + fadeIn(),
        exit = shrinkVertically(animationSpec = tween(500)) + fadeOut()
    ) {
        AuthTextField(
            fieldType = FieldType.USERNAME,
            value = value,
            onValueChanged = {
                onValueChanged(it)
            },
            error = error,
            onTextFieldIconClick = onTextFieldIconClick
        )
    }
}

@Composable
fun AuthCard(
    authType: AuthType,
    textFieldsState: TextFieldsState,
    textFieldsErrorsState: TextFieldsErrorsState,
    onAuthTypeChanged: (AuthType) -> Unit,
    onNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onButtonClick: () -> Unit,
    onTextFieldIconClick: (FieldType) -> Unit
) {

    var isAnimated by remember { mutableStateOf(false) }

    val offsetY by animateDpAsState(
        targetValue = if (isAnimated) 0.dp else 600.dp,
        animationSpec = tween(durationMillis = 1000), label = ""
    )

    LaunchedEffect(Unit) {
        isAnimated = true
    }

    ElevatedCard(
        modifier = Modifier
            .padding(start = 30.dp, end = 30.dp)
            .offset {
                IntOffset(0, offsetY.roundToPx())
            }
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CardFirstText(authType)

            AnimatedNickNameTextField(
                authType = authType,
                value = textFieldsState.userName,
                error = textFieldsErrorsState.userNameError,
                onValueChanged = { onNameChanged(it) },
                onTextFieldIconClick = onTextFieldIconClick
            )

            AuthTextField(
                fieldType = FieldType.EMAIL,
                value = textFieldsState.email,
                onValueChanged = {
                    onEmailChanged(it)
                },
                error = textFieldsErrorsState.emailError,
                onTextFieldIconClick = onTextFieldIconClick
            )
            AnimatedPasswordTextField(
                authType = authType,
                value = textFieldsState.password,
                error = textFieldsErrorsState.passwordError,
                isPasswordVisible = textFieldsState.isPasswordVisible,
                onValueChanged = { onPasswordChanged(it) },
                onTextFieldIconClick = onTextFieldIconClick
            )

            AuthButton(
                authType = authType,
                onButtonClick = {
                    onButtonClick()
                })
            ChangeAuthAndReset(
                authType = authType,
                onAuthTypeChanged = {
                    onAuthTypeChanged(it)
                }
            )

        }
    }
}




