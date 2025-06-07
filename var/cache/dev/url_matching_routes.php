<?php

/**
 * This file has been auto-generated
 * by the Symfony Routing Component.
 */

return [
    false, // $matchHost
    [ // $staticRoutes
        '/admin/user' => [[['_route' => '_user_index', '_locale' => 'en', '_controller' => 'App\\Controller\\Admin\\UserCrudController::index', 'routeCreatedByEasyAdmin' => true, 'dashboardControllerFqcn' => 'App\\Controller\\Admin\\DashboardController', 'crudControllerFqcn' => 'App\\Controller\\Admin\\UserCrudController', 'crudAction' => 'index'], null, ['GET' => 0], null, false, false, null]],
        '/admin/user/new' => [[['_route' => '_user_new', '_locale' => 'en', '_controller' => 'App\\Controller\\Admin\\UserCrudController::new', 'routeCreatedByEasyAdmin' => true, 'dashboardControllerFqcn' => 'App\\Controller\\Admin\\DashboardController', 'crudControllerFqcn' => 'App\\Controller\\Admin\\UserCrudController', 'crudAction' => 'new'], null, ['GET' => 0, 'POST' => 1], null, false, false, null]],
        '/admin/user/batch-delete' => [[['_route' => '_user_batch_delete', '_locale' => 'en', '_controller' => 'App\\Controller\\Admin\\UserCrudController::batchDelete', 'routeCreatedByEasyAdmin' => true, 'dashboardControllerFqcn' => 'App\\Controller\\Admin\\DashboardController', 'crudControllerFqcn' => 'App\\Controller\\Admin\\UserCrudController', 'crudAction' => 'batchDelete'], null, ['POST' => 0], null, false, false, null]],
        '/admin/user/autocomplete' => [[['_route' => '_user_autocomplete', '_locale' => 'en', '_controller' => 'App\\Controller\\Admin\\UserCrudController::autocomplete', 'routeCreatedByEasyAdmin' => true, 'dashboardControllerFqcn' => 'App\\Controller\\Admin\\DashboardController', 'crudControllerFqcn' => 'App\\Controller\\Admin\\UserCrudController', 'crudAction' => 'autocomplete'], null, ['GET' => 0], null, false, false, null]],
        '/admin/user/render-filters' => [[['_route' => '_user_render_filters', '_locale' => 'en', '_controller' => 'App\\Controller\\Admin\\UserCrudController::renderFilters', 'routeCreatedByEasyAdmin' => true, 'dashboardControllerFqcn' => 'App\\Controller\\Admin\\DashboardController', 'crudControllerFqcn' => 'App\\Controller\\Admin\\UserCrudController', 'crudAction' => 'renderFilters'], null, ['GET' => 0], null, false, false, null]],
        '/_profiler' => [[['_route' => '_profiler_home', '_controller' => 'web_profiler.controller.profiler::homeAction'], null, null, null, true, false, null]],
        '/_profiler/search' => [[['_route' => '_profiler_search', '_controller' => 'web_profiler.controller.profiler::searchAction'], null, null, null, false, false, null]],
        '/_profiler/search_bar' => [[['_route' => '_profiler_search_bar', '_controller' => 'web_profiler.controller.profiler::searchBarAction'], null, null, null, false, false, null]],
        '/_profiler/phpinfo' => [[['_route' => '_profiler_phpinfo', '_controller' => 'web_profiler.controller.profiler::phpinfoAction'], null, null, null, false, false, null]],
        '/_profiler/xdebug' => [[['_route' => '_profiler_xdebug', '_controller' => 'web_profiler.controller.profiler::xdebugAction'], null, null, null, false, false, null]],
        '/_profiler/open' => [[['_route' => '_profiler_open_file', '_controller' => 'web_profiler.controller.profiler::openAction'], null, null, null, false, false, null]],
        '/admin' => [
            [['_route' => 'app_admin', '_controller' => 'App\\Controller\\AdminController::index'], null, null, null, false, false, null],
            [['_route' => 'admin_dashboard', '_controller' => 'App\\Controller\\Admin\\DashboardController::admin'], null, null, null, false, false, null],
            [['_route' => 'app_admin_dashboard_index', '_controller' => 'App\\Controller\\Admin\\DashboardController::index'], null, null, null, false, false, null],
        ],
        '/admin/blocked-users' => [[['_route' => 'admin_blocked_users', '_controller' => 'App\\Controller\\BlockedUsersController::blockedUsers'], null, null, null, false, false, null]],
        '/candidature' => [
            [['_route' => 'app_candidatureoffre', '_controller' => 'App\\Controller\\CandidatureController::index'], null, null, null, false, false, null],
            [['_route' => 'app_candidaturemission', '_controller' => 'App\\Controller\\CandidaturemissionController::index'], null, null, null, false, false, null],
        ],
        '/showcandidature' => [[['_route' => 'app_showcandidature', '_controller' => 'App\\Controller\\CandidatureController::showcandidature'], null, null, null, false, false, null]],
        '/acceptes' => [[['_route' => 'app_acceptees', '_controller' => 'App\\Controller\\CandidatureController::Acceptees'], null, null, null, false, false, null]],
        '/rejetes' => [[['_route' => 'app_rejetees', '_controller' => 'App\\Controller\\CandidatureController::Rejetees'], null, null, null, false, false, null]],
        '/candidatures' => [[['_route' => 'app_lister_toutes_candidatures', '_controller' => 'App\\Controller\\CandidaturemissionController::listerToutesCandidatures'], null, null, null, false, false, null]],
        '/chatbot' => [[['_route' => 'chatbot_page', '_controller' => 'App\\Controller\\ChatbotController::chatbotPage'], null, ['GET' => 0], null, false, false, null]],
        '/chatbot/api' => [[['_route' => 'chatbot_api', '_controller' => 'App\\Controller\\ChatbotController::api'], null, ['POST' => 0], null, false, false, null]],
        '/recherche-offre-mission' => [[['_route' => 'app_recherche_offre_mission', '_controller' => 'App\\Controller\\CherchermissionController::recherche'], null, null, null, false, false, null]],
        '/profilechercheur' => [[['_route' => 'app_chercheurprofile', '_controller' => 'App\\Controller\\ChercheurController::profile'], null, null, null, false, false, null]],
        '/chercheur' => [[['_route' => 'app_chercheur', '_controller' => 'App\\Controller\\ChercheurController::index'], null, null, null, false, false, null]],
        '/community' => [[['_route' => 'app_community', '_controller' => 'App\\Controller\\CommunityController::index'], null, null, null, false, false, null]],
        '/demande/mission' => [[['_route' => 'app_demande_mission_index', '_controller' => 'App\\Controller\\DemandeMissionController::index'], null, ['GET' => 0], null, false, false, null]],
        '/demande/mission/new' => [[['_route' => 'app_demande_mission_new', '_controller' => 'App\\Controller\\DemandeMissionController::new'], null, ['GET' => 0, 'POST' => 1], null, false, false, null]],
        '/event' => [[['_route' => 'app_event', '_controller' => 'App\\Controller\\EvenementsController::index'], null, null, null, false, false, null]],
        '/events' => [[['_route' => 'app_events', '_controller' => 'App\\Controller\\EventModController::index'], null, null, null, false, false, null]],
        '/new' => [[['_route' => 'evenement_new', '_controller' => 'App\\Controller\\EventModController::new'], null, null, null, false, false, null]],
        '/api/evenements/recherche' => [[['_route' => 'api_evenements_recherche', '_controller' => 'App\\Controller\\EventModController::recherche'], null, ['GET' => 0], null, false, false, null]],
        '/type' => [[['_route' => 'type_index', '_controller' => 'App\\Controller\\EventTypeController::index'], null, null, null, false, false, null]],
        '/addtypeEvent' => [[['_route' => 'type_new', '_controller' => 'App\\Controller\\EventTypeController::new'], null, null, null, false, false, null]],
        '/forum' => [[['_route' => 'app_forum', '_controller' => 'App\\Controller\\ForumController::index'], null, null, null, false, false, null]],
        '/addDiscussion' => [[['_route' => 'app_addDiscussion', '_controller' => 'App\\Controller\\ForumController::addDiscussion'], null, null, null, false, false, null]],
        '/discussion/stats' => [[['_route' => 'discussion_stats', '_controller' => 'App\\Controller\\ForumController::discussionStats'], null, ['GET' => 0], null, false, false, null]],
        '/profilefreelancer' => [[['_route' => 'app_profilefreelancer', '_controller' => 'App\\Controller\\FreelancerController::profile'], null, null, null, false, false, null]],
        '/freelancer' => [[['_route' => 'app_freelancer', '_controller' => 'App\\Controller\\FreelancerController::index'], null, null, null, false, false, null]],
        '/home' => [[['_route' => 'app_home', '_controller' => 'App\\Controller\\HomeController::index'], null, null, null, false, false, null]],
        '/' => [[['_route' => 'app_index', '_controller' => 'App\\Controller\\IndexController::index'], null, null, null, false, false, null]],
        '/indexhome' => [[['_route' => 'app_indexhome', '_controller' => 'App\\Controller\\IndexController::indexhome'], null, null, null, false, false, null]],
        '/offrefrelencer' => [[['_route' => 'app_offrefrelencer_index', '_controller' => 'App\\Controller\\MissionfreelencerController::index'], null, ['GET' => 0], null, false, false, null]],
        '/offrefrelencer/new' => [[['_route' => 'app_offrefrelencer_new', '_controller' => 'App\\Controller\\MissionfreelencerController::new'], null, ['GET' => 0, 'POST' => 1], null, false, false, null]],
        '/profilemoderateur' => [[['_route' => 'app_profilemoderateur', '_controller' => 'App\\Controller\\ModerateurController::profile'], null, null, null, false, false, null]],
        '/moderateur' => [[['_route' => 'app_moderateur', '_controller' => 'App\\Controller\\ModerateurController::index'], null, null, null, false, false, null]],
        '/notification' => [[['_route' => 'app_notification', '_controller' => 'App\\Controller\\NotificationController::index'], null, null, null, false, false, null]],
        '/recherche' => [[['_route' => 'app_recherche', '_controller' => 'App\\Controller\\OffreController::recherche'], null, null, null, false, false, null]],
        '/offremod' => [[['_route' => 'app_offremod', '_controller' => 'App\\Controller\\OffremodController::index'], null, null, null, false, false, null]],
        '/ajoutoffre' => [[['_route' => 'app_ajoutoffre', '_controller' => 'App\\Controller\\OffremodController::ajoutoffre'], null, null, null, false, false, null]],
        '/statistiques' => [[['_route' => 'app_statistiques', '_controller' => 'App\\Controller\\OffremodController::statistiques'], null, null, null, false, false, null]],
        '/export/pdf2' => [[['_route' => 'app_export_pdf2', '_controller' => 'App\\Controller\\OffremodController::exportToPdf2'], null, null, null, false, false, null]],
        '/register' => [[['_route' => 'app_register', '_controller' => 'App\\Controller\\RegistrationController::register'], null, ['GET' => 0, 'POST' => 1], null, false, false, null]],
        '/reset-password' => [[['_route' => 'app_forgot_password_request', '_controller' => 'App\\Controller\\ResetPasswordController::request'], null, null, null, false, false, null]],
        '/reset-password/check-email' => [[['_route' => 'app_check_email', '_controller' => 'App\\Controller\\ResetPasswordController::checkEmail'], null, null, null, false, false, null]],
        '/login' => [[['_route' => 'app_login', '_controller' => 'App\\Controller\\SecurityController::login'], null, ['GET' => 0, 'POST' => 1], null, false, false, null]],
        '/login/redirect' => [[['_route' => 'app_login_redirect', '_controller' => 'App\\Controller\\SecurityController::redirectAfterLogin'], null, null, null, false, false, null]],
        '/profile' => [[['_route' => 'app_profile', '_controller' => 'App\\Controller\\SecurityController::profile'], null, null, null, false, false, null]],
        '/logout' => [[['_route' => 'app_logout', '_controller' => 'App\\Controller\\SecurityController::logout'], null, null, null, false, false, null]],
        '/typecontrat' => [[['_route' => 'app_typecontrat', '_controller' => 'App\\Controller\\TypecontratController::index'], null, null, null, false, false, null]],
        '/showtypecontrat' => [[['_route' => 'app_showtypecontrat', '_controller' => 'App\\Controller\\TypecontratController::showtypecontrat'], null, null, null, false, false, null]],
        '/addtypecontrat' => [[['_route' => 'app_addtypecontrat', '_controller' => 'App\\Controller\\TypecontratController::addtypecontrat'], null, null, null, false, false, null]],
        '/typeoffre' => [[['_route' => 'app_typeoffre', '_controller' => 'App\\Controller\\TypeoffreController::index'], null, null, null, false, false, null]],
        '/showtypeoffre' => [[['_route' => 'app_showtypeoffre', '_controller' => 'App\\Controller\\TypeoffreController::showtypeoffre'], null, null, null, false, false, null]],
        '/addtypeoffre' => [[['_route' => 'app_addtypeoffre', '_controller' => 'App\\Controller\\TypeoffreController::addtypeoffre'], null, null, null, false, false, null]],
        '/user/data' => [[['_route' => 'user_data', '_controller' => 'App\\Controller\\UserDataController::getUserData'], null, ['GET' => 0], null, false, false, null]],
        '/profile/chercheur' => [[['_route' => 'app_profilechercheur', '_controller' => 'App\\Controller\\ChercheurController::profile'], null, null, null, false, false, null]],
    ],
    [ // $regexpList
        0 => '{^(?'
                .'|/a(?'
                    .'|d(?'
                        .'|min/user/([^/]++)(?'
                            .'|/(?'
                                .'|edit(*:44)'
                                .'|delete(*:57)'
                            .')'
                            .'|(*:65)'
                        .')'
                        .'|d(?'
                            .'|candidature/([^/]++)(*:97)'
                            .'|Reply/([^/]++)(*:118)'
                        .')'
                    .')'
                    .'|ccept/([^/]++)(*:142)'
                    .'|nnuler/([^/]++)(*:165)'
                .')'
                .'|/_(?'
                    .'|error/(\\d+)(?:\\.([^/]++))?(*:205)'
                    .'|wdt/([^/]++)(*:225)'
                    .'|profiler/(?'
                        .'|font/([^/\\.]++)\\.woff2(*:267)'
                        .'|([^/]++)(?'
                            .'|/(?'
                                .'|search/results(*:304)'
                                .'|router(*:318)'
                                .'|exception(?'
                                    .'|(*:338)'
                                    .'|\\.css(*:351)'
                                .')'
                            .')'
                            .'|(*:361)'
                        .')'
                    .')'
                .')'
                .'|/update(?'
                    .'|c(?'
                        .'|andidature/([^/]++)(*:405)'
                        .'|hercheur/([^/]++)(*:430)'
                    .')'
                    .'|freelancer/([^/]++)(*:458)'
                    .'|moderateur/([^/]++)(*:485)'
                .')'
                .'|/de(?'
                    .'|l(?'
                        .'|ete(?'
                            .'|c(?'
                                .'|andidature/([^/]++)(*:533)'
                                .'|hercheur/([^/]++)(*:558)'
                            .')'
                            .'|freelancer/([^/]++)(*:586)'
                            .'|moderateur/([^/]++)(*:613)'
                        .')'
                        .'|Event/([^/]++)(*:636)'
                        .'|typeEvent/([^/]++)(*:662)'
                    .')'
                    .'|mande/mission/(?'
                        .'|(\\d+)(*:693)'
                        .'|demande/([^/]++)(*:717)'
                        .'|([^/]++)(?'
                            .'|/edit(*:741)'
                            .'|(*:749)'
                        .')'
                        .'|mes\\-candidatures(*:775)'
                    .')'
                .')'
                .'|/reject/([^/]++)(*:801)'
                .'|/e(?'
                    .'|xport/pdf/([^/]++)(*:832)'
                    .'|vent/([^/]++)(*:853)'
                    .'|dit(?'
                        .'|/([^/]++)(*:876)'
                        .'|Type/([^/]++)(*:897)'
                    .')'
                .')'
                .'|/p(?'
                    .'|ostuler/([^/]++)(*:928)'
                    .'|articiper/([^/]++)(*:954)'
                .')'
                .'|/candidature/([^/]++)/(?'
                    .'|evaluer(*:995)'
                    .'|([^/]++)(*:1011)'
                .')'
                .'|/Delete(?'
                    .'|Discussion/([^/]++)(*:1050)'
                    .'|Reply/([^/]++)/([^/]++)(*:1082)'
                .')'
                .'|/U(?'
                    .'|pdate(?'
                        .'|Discussion/([^/]++)(*:1124)'
                        .'|Reply/([^/]++)/([^/]++)(*:1156)'
                    .')'
                    .'|nAdd(?'
                        .'|Like/([^/]++)(*:1186)'
                        .'|DisLike(?'
                            .'|/([^/]++)(*:1214)'
                            .'|ToReply/([^/]++)(*:1239)'
                        .')'
                    .')'
                .')'
                .'|/Add(?'
                    .'|Like(?'
                        .'|/([^/]++)(*:1274)'
                        .'|ToReply/([^/]++)(*:1299)'
                    .')'
                    .'|DisLike(?'
                        .'|/([^/]++)(*:1328)'
                        .'|ToReply/([^/]++)(*:1353)'
                    .')'
                .')'
                .'|/forum/details/([^/]++)(*:1387)'
                .'|/messages/([^/]++)(*:1414)'
                .'|/se(?'
                    .'|nd/([^/]++)(*:1440)'
                    .'|tNotificationTrue/([^/]++)(*:1475)'
                .')'
                .'|/offrefrelencer/(?'
                    .'|([^/]++)(?'
                        .'|(*:1515)'
                        .'|/edit(*:1529)'
                        .'|(*:1538)'
                    .')'
                    .'|offre/(?'
                        .'|([^/]++)(*:1565)'
                        .'|publier/([^/]++)(*:1590)'
                    .')'
                    .'|candidature/([^/]++)(*:1620)'
                .')'
                .'|/(en|fr)/offre(*:1644)'
                .'|/postuler/([^/]++)(*:1671)'
                .'|/modifoffre/([^/]++)(*:1700)'
                .'|/supprimeroffre/([^/]++)(*:1733)'
                .'|/reset\\-password/reset(?:/([^/]++))?(*:1778)'
                .'|/updatetype(?'
                    .'|contrat/([^/]++)(*:1817)'
                    .'|offre/([^/]++)(*:1840)'
                .')'
                .'|/deletetype(?'
                    .'|contrat/([^/]++)(*:1880)'
                    .'|offre/([^/]++)(*:1903)'
                .')'
            .')/?$}sDu',
    ],
    [ // $dynamicRoutes
        44 => [[['_route' => '_user_edit', '_locale' => 'en', '_controller' => 'App\\Controller\\Admin\\UserCrudController::edit', 'routeCreatedByEasyAdmin' => true, 'dashboardControllerFqcn' => 'App\\Controller\\Admin\\DashboardController', 'crudControllerFqcn' => 'App\\Controller\\Admin\\UserCrudController', 'crudAction' => 'edit'], ['entityId'], ['GET' => 0, 'POST' => 1, 'PATCH' => 2], null, false, false, null]],
        57 => [[['_route' => '_user_delete', '_locale' => 'en', '_controller' => 'App\\Controller\\Admin\\UserCrudController::delete', 'routeCreatedByEasyAdmin' => true, 'dashboardControllerFqcn' => 'App\\Controller\\Admin\\DashboardController', 'crudControllerFqcn' => 'App\\Controller\\Admin\\UserCrudController', 'crudAction' => 'delete'], ['entityId'], ['POST' => 0], null, false, false, null]],
        65 => [[['_route' => '_user_detail', '_locale' => 'en', '_controller' => 'App\\Controller\\Admin\\UserCrudController::detail', 'routeCreatedByEasyAdmin' => true, 'dashboardControllerFqcn' => 'App\\Controller\\Admin\\DashboardController', 'crudControllerFqcn' => 'App\\Controller\\Admin\\UserCrudController', 'crudAction' => 'detail'], ['entityId'], ['GET' => 0], null, false, true, null]],
        97 => [[['_route' => 'app_addcandidature', '_controller' => 'App\\Controller\\CandidatureController::addcandidature'], ['id'], null, null, false, true, null]],
        118 => [[['_route' => 'app_addReply', '_controller' => 'App\\Controller\\ForumDetailsController::AddReply'], ['id'], null, null, false, true, null]],
        142 => [[['_route' => 'app_accept', '_controller' => 'App\\Controller\\CandidatureController::accept'], ['id'], null, null, false, true, null]],
        165 => [[['_route' => 'evenement_annuler', '_controller' => 'App\\Controller\\EventModController::annuler'], ['id'], ['POST' => 0], null, false, true, null]],
        205 => [[['_route' => '_preview_error', '_controller' => 'error_controller::preview', '_format' => 'html'], ['code', '_format'], null, null, false, true, null]],
        225 => [[['_route' => '_wdt', '_controller' => 'web_profiler.controller.profiler::toolbarAction'], ['token'], null, null, false, true, null]],
        267 => [[['_route' => '_profiler_font', '_controller' => 'web_profiler.controller.profiler::fontAction'], ['fontName'], null, null, false, false, null]],
        304 => [[['_route' => '_profiler_search_results', '_controller' => 'web_profiler.controller.profiler::searchResultsAction'], ['token'], null, null, false, false, null]],
        318 => [[['_route' => '_profiler_router', '_controller' => 'web_profiler.controller.router::panelAction'], ['token'], null, null, false, false, null]],
        338 => [[['_route' => '_profiler_exception', '_controller' => 'web_profiler.controller.exception_panel::body'], ['token'], null, null, false, false, null]],
        351 => [[['_route' => '_profiler_exception_css', '_controller' => 'web_profiler.controller.exception_panel::stylesheet'], ['token'], null, null, false, false, null]],
        361 => [[['_route' => '_profiler', '_controller' => 'web_profiler.controller.profiler::panelAction'], ['token'], null, null, false, true, null]],
        405 => [[['_route' => 'app_updatecandidature', '_controller' => 'App\\Controller\\CandidatureController::updatecandidature'], ['id'], null, null, false, true, null]],
        430 => [[['_route' => 'app_updatechercheur', '_controller' => 'App\\Controller\\ChercheurController::updatechercheur'], ['id'], null, null, false, true, null]],
        458 => [[['_route' => 'app_updatefreelancer', '_controller' => 'App\\Controller\\FreelancerController::updatefreelancer'], ['id'], null, null, false, true, null]],
        485 => [[['_route' => 'app_updatemoderateur', '_controller' => 'App\\Controller\\ModerateurController::updatemoderateur'], ['id'], null, null, false, true, null]],
        533 => [[['_route' => 'app_deletecandidature', '_controller' => 'App\\Controller\\CandidatureController::deletecandidature'], ['id'], null, null, false, true, null]],
        558 => [[['_route' => 'app_deletechercheur', '_controller' => 'App\\Controller\\ChercheurController::deletechercheur'], ['id'], null, null, false, true, null]],
        586 => [[['_route' => 'app_deletefreelancer', '_controller' => 'App\\Controller\\FreelancerController::deletefreelancer'], ['id'], null, null, false, true, null]],
        613 => [[['_route' => 'app_deletemoderateur', '_controller' => 'App\\Controller\\ModerateurController::deletemoderateur'], ['id'], null, null, false, true, null]],
        636 => [[['_route' => 'event_del', '_controller' => 'App\\Controller\\EventModController::supprimeroffre'], ['id'], null, null, false, true, null]],
        662 => [[['_route' => 'typeEvent_delete', '_controller' => 'App\\Controller\\EventTypeController::supprimeroffre'], ['id'], null, null, false, true, null]],
        693 => [[['_route' => 'voir_demande_details', '_controller' => 'App\\Controller\\CandidaturemissionController::voirDemandeDetails'], ['id'], null, null, false, true, null]],
        717 => [[['_route' => 'app_demande_mission_show', '_controller' => 'App\\Controller\\DemandeMissionController::show'], ['id'], ['GET' => 0], null, false, true, null]],
        741 => [[['_route' => 'app_demande_mission_edit', '_controller' => 'App\\Controller\\DemandeMissionController::edit'], ['id'], ['GET' => 0, 'POST' => 1], null, false, false, null]],
        749 => [[['_route' => 'app_demande_mission_delete', '_controller' => 'App\\Controller\\DemandeMissionController::delete'], ['id'], ['POST' => 0], null, false, true, null]],
        775 => [[['_route' => 'app_mes_candidatures', '_controller' => 'App\\Controller\\DemandeMissionController::consulterCandidatures'], [], ['GET' => 0], null, false, false, null]],
        801 => [[['_route' => 'app_reject', '_controller' => 'App\\Controller\\CandidatureController::rejectCandidature'], ['id'], null, null, false, true, null]],
        832 => [[['_route' => 'app_export_pdf', '_controller' => 'App\\Controller\\CandidatureController::exportToPdf'], ['id'], null, null, false, true, null]],
        853 => [[['_route' => 'app_event_show', '_controller' => 'App\\Controller\\EvenementsController::show'], ['id'], null, null, false, true, null]],
        876 => [[['_route' => 'evenement_edit', '_controller' => 'App\\Controller\\EventModController::edit'], ['id'], ['GET' => 0, 'POST' => 1], null, false, true, null]],
        897 => [[['_route' => 'type_edit', '_controller' => 'App\\Controller\\EventTypeController::edit'], ['id'], ['GET' => 0, 'POST' => 1], null, false, true, null]],
        928 => [[['_route' => 'app_postuler_offre', '_controller' => 'App\\Controller\\CandidaturemissionController::postulerAOffre'], ['offreId'], null, null, false, true, null]],
        954 => [[['_route' => 'evenement_participer', '_controller' => 'App\\Controller\\EventModController::participer'], ['id'], ['POST' => 0], null, false, true, null]],
        995 => [[['_route' => 'evaluer_candidature', '_controller' => 'App\\Controller\\CandidaturemissionController::evaluerCandidature'], ['id'], ['POST' => 0], null, false, false, null]],
        1011 => [[['_route' => 'accepter_refuser_candidature', '_controller' => 'App\\Controller\\CandidaturemissionController::accepterOuRefuserCandidature'], ['id', 'action'], ['POST' => 0], null, false, true, null]],
        1050 => [[['_route' => 'app_deleteDiscussion', '_controller' => 'App\\Controller\\ForumController::deleteDiscussion'], ['id'], null, null, false, true, null]],
        1082 => [[['_route' => 'app_deleteReply', '_controller' => 'App\\Controller\\ForumDetailsController::deleteReply'], ['discussionId', 'replyId'], null, null, false, true, null]],
        1124 => [[['_route' => 'app_updateDiscussion', '_controller' => 'App\\Controller\\ForumController::updateDiscussion'], ['id'], null, null, false, true, null]],
        1156 => [[['_route' => 'app_UpdateReply', '_controller' => 'App\\Controller\\ForumDetailsController::UpdateReply'], ['discussionId', 'replyId'], null, null, false, true, null]],
        1186 => [[['_route' => 'app_UnAddLike', '_controller' => 'App\\Controller\\ForumController::UnAddLike'], ['id'], null, null, false, true, null]],
        1214 => [[['_route' => 'app_UnAddDisLike', '_controller' => 'App\\Controller\\ForumController::UnAddDisLike'], ['id'], null, null, false, true, null]],
        1239 => [[['_route' => 'app_UnAddDisLikeToReply', '_controller' => 'App\\Controller\\ForumDetailsController::UnAddDisLike'], ['id'], null, null, false, true, null]],
        1274 => [[['_route' => 'app_AddLike', '_controller' => 'App\\Controller\\ForumController::AddLike'], ['id'], null, null, false, true, null]],
        1299 => [[['_route' => 'app_AddLikeToReply', '_controller' => 'App\\Controller\\ForumDetailsController::AddLike'], ['id'], null, null, false, true, null]],
        1328 => [[['_route' => 'app_AddDisLike', '_controller' => 'App\\Controller\\ForumController::AddDisLike'], ['id'], null, null, false, true, null]],
        1353 => [[['_route' => 'app_AdddisLikeToReply', '_controller' => 'App\\Controller\\ForumDetailsController::AddDisLike'], ['id'], null, null, false, true, null]],
        1387 => [[['_route' => 'app_forum_details', '_controller' => 'App\\Controller\\ForumDetailsController::index'], ['id'], null, null, false, true, null]],
        1414 => [[['_route' => 'get_conversation', '_controller' => 'App\\Controller\\MessageController::getConversation'], ['messager2Id'], ['GET' => 0], null, false, true, null]],
        1440 => [[['_route' => 'send_message', '_controller' => 'App\\Controller\\MessageController::sendMessage'], ['messager2Id'], ['POST' => 0], null, false, true, null]],
        1475 => [[['_route' => 'app_setNotificationTrue', '_controller' => 'App\\Controller\\NotificationController::markAsRead'], ['id'], null, null, false, true, null]],
        1515 => [[['_route' => 'app_offrefrelencer_show', '_controller' => 'App\\Controller\\MissionfreelencerController::show'], ['id'], ['GET' => 0], null, false, true, null]],
        1529 => [[['_route' => 'app_offrefrelencer_edit', '_controller' => 'App\\Controller\\MissionfreelencerController::edit'], ['id'], ['GET' => 0, 'POST' => 1], null, false, false, null]],
        1538 => [[['_route' => 'app_offrefrelencer_delete', '_controller' => 'App\\Controller\\MissionfreelencerController::delete'], ['id'], ['POST' => 0], null, false, true, null]],
        1565 => [[['_route' => 'app_offre_details', '_controller' => 'App\\Controller\\MissionfreelencerController::showOfferDetails'], ['id'], null, null, false, true, null]],
        1590 => [[['_route' => 'app_offre_publier', '_controller' => 'App\\Controller\\MissionfreelencerController::publierOffre'], ['id'], null, null, false, true, null]],
        1620 => [[['_route' => 'app_consulter_candidature', '_controller' => 'App\\Controller\\MissionfreelencerController::consulterCandidature'], ['id'], ['GET' => 0], null, false, true, null]],
        1644 => [[['_route' => 'app_offre', '_locale' => 'fr', '_controller' => 'App\\Controller\\OffreController::index'], ['_locale'], null, null, false, false, null]],
        1671 => [[['_route' => 'app_postulerhedir', '_controller' => 'App\\Controller\\OffreController::postuler'], ['id'], null, null, false, true, null]],
        1700 => [[['_route' => 'app_modifoffre', '_controller' => 'App\\Controller\\OffremodController::modifoffre'], ['id'], null, null, false, true, null]],
        1733 => [[['_route' => 'app_supprimeroffre', '_controller' => 'App\\Controller\\OffremodController::supprimeroffre'], ['id'], null, null, false, true, null]],
        1778 => [[['_route' => 'app_reset_password', 'token' => null, '_controller' => 'App\\Controller\\ResetPasswordController::reset'], ['token'], null, null, false, true, null]],
        1817 => [[['_route' => 'app_updatetypecontrat', '_controller' => 'App\\Controller\\TypecontratController::updatetypecontrat'], ['id'], null, null, false, true, null]],
        1840 => [[['_route' => 'app_updatetypeoffre', '_controller' => 'App\\Controller\\TypeoffreController::updatetypeoffre'], ['id'], null, null, false, true, null]],
        1880 => [[['_route' => 'app_deletetypecontrat', '_controller' => 'App\\Controller\\TypecontratController::deletetypecontrat'], ['id'], null, null, false, true, null]],
        1903 => [
            [['_route' => 'app_deletetypeoffre', '_controller' => 'App\\Controller\\TypeoffreController::deletetypeoffre'], ['id'], null, null, false, true, null],
            [null, null, null, null, false, false, 0],
        ],
    ],
    null, // $checkCondition
];
