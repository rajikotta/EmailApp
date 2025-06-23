package com.raji.email

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.raji.core_ui.component.DetailsAppBar
import com.raji.core_ui.component.DrawerCategory
import com.raji.core_ui.component.DrawerDivider
import com.raji.core_ui.component.DrawerMenuItem
import com.raji.core_ui.component.DrawerTitleItem
import com.raji.core_ui.component.HomeAppBar
import com.raji.core_ui.content.getDrawerItems
import com.raji.core_ui.theme.EmailTheme
import com.raji.core_ui.uimodel.DrawerData
import com.raji.email.navigation.EmailDetails
import com.raji.email.navigation.EmailList
import com.raji.presentation.detail.EmailDetailViewModel
import com.raji.presentation.detail.EmailDetailsScreen
import com.raji.presentation.list.EmailListContract
import com.raji.presentation.list.EmailListScreen
import com.raji.presentation.list.EmailListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            EmailTheme {


                val navController = rememberNavController()
                var topAppbarState by remember { mutableStateOf(com.raji.email.TopAppBarState.HOME) }
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()

                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = { DrawerContent() },
                    scrimColor = MaterialTheme.colorScheme.surfaceContainer.copy(alpha = 0.6f),
                    content = {
                        Scaffold(
                            modifier = Modifier.fillMaxSize(),
                            topBar = {
                                when (topAppbarState) {
                                    TopAppBarState.HOME -> HomeAppBar(
                                        modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars)
                                    ) {
                                        scope.launch {
                                            drawerState.open()
                                        }
                                    }

                                    TopAppBarState.DETAILS -> DetailsAppBar(
                                        navController = navController,
                                        modifier = Modifier.windowInsetsPadding(
                                            WindowInsets.statusBars
                                        )
                                    )
                                }
                            },
                            bottomBar = {
                                BottomAppBar(modifier = Modifier) {

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceEvenly
                                    ) {
                                        IconButton(onClick = {
                                            Toast.makeText(
                                                this@MainActivity,
                                                "Email Clicked",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }) {
                                            Icon(
                                                imageVector = Icons.Filled.Email,
                                                contentDescription = stringResource(R.string.accessibility_email)
                                            )
                                        }
                                        IconButton(onClick = {
                                            Toast.makeText(
                                                this@MainActivity,
                                                "Video Call Clicked",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }) {
                                            Icon(
                                                imageVector = Icons.Filled.Videocam,
                                                contentDescription = "Video Call"
                                            )
                                        }
                                    }

                                }
                            }
                        ) { innerPadding ->
                            NavHost(
                                navController = navController,
                                startDestination = EmailList,
                                modifier = Modifier.padding(innerPadding)
                            ) {
                                composable<EmailList>(
                                    exitTransition = {
                                        return@composable slideOutOfContainer(
                                            AnimatedContentTransitionScope.SlideDirection.Start,
                                            tween(500)
                                        )
                                    },
                                    popEnterTransition = {
                                        return@composable slideIntoContainer(
                                            AnimatedContentTransitionScope.SlideDirection.End,
                                            tween(500)
                                        )
                                    }
                                ) {
                                    topAppbarState = TopAppBarState.HOME
                                    val viewModel: EmailListViewModel = hiltViewModel()
                                    val state by viewModel.state.collectAsStateWithLifecycle()
                                    val dispatch: (EmailListContract.EmailListEvent) -> Unit =
                                        { event ->
                                            viewModel.event(event)
                                        }

                                    LaunchedEffect(key1 = Unit) {
                                        viewModel.effect.collect { it ->
                                            when (it) {
                                                is EmailListContract.EmailListEffect.NavigateToDetail -> with(
                                                    it
                                                ) {
                                                    navController.navigate(
                                                        EmailDetails(
                                                            from = email.from,
                                                            profileImage = email.profileImage,
                                                            subject = email.subject,
                                                            isPromotional = email.isPromotional,
                                                            isStarred = email.isStarred
                                                        )
                                                    )
                                                }

                                            }
                                        }
                                    }

                                    EmailListScreen(
                                        state = state,
                                        dispatch = dispatch
                                    )
                                }
                                composable<EmailDetails>(
                                    enterTransition = {
                                        return@composable slideIntoContainer(
                                            AnimatedContentTransitionScope.SlideDirection.Start,
                                            tween(500)
                                        )
                                    },
                                    popExitTransition = {
                                        return@composable slideOutOfContainer(
                                            AnimatedContentTransitionScope.SlideDirection.End,
                                            tween(500)
                                        )
                                    }) { backStackEntry ->


                                    topAppbarState = TopAppBarState.DETAILS
                                    val args = backStackEntry.toRoute<EmailDetails>()
                                    val viewModel: EmailDetailViewModel = hiltViewModel()
                                    val state by viewModel.state.collectAsStateWithLifecycle()

                                    EmailDetailsScreen(
                                        state = state,
                                        from = args.from,
                                        profileImage = args.profileImage,
                                        subject = args.subject,
                                        isPromotional = args.isPromotional
                                    )

                                }
                            }


                        }
                    })
            }
        }
    }


    @Composable
    fun DrawerContent(modifier: Modifier = Modifier) {
        val drawerItemList = getDrawerItems()
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.8f)
                .background(MaterialTheme.colorScheme.background)
        ) {
            LazyColumn {

                items(drawerItemList) {
                    when (it) {
                        is DrawerData.Category -> DrawerCategory(it.title)
                        DrawerData.Divider -> DrawerDivider()
                        is DrawerData.Item -> DrawerMenuItem(it.title, it.icon) {

                        }

                        is DrawerData.Title -> DrawerTitleItem(it.title)
                    }
                }
            }

        }
    }
}