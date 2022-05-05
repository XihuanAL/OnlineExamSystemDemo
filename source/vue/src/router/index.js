import {createRouter, createWebHistory} from 'vue-router'

const routes = [
    {
        path: '/dashboard',
        component: () => import('../views/admin/Dashboard.vue'),
        redirect:"/subject",
        children: [
            {
                path: '/exam',

                component: () => import('../views/admin/management/ExamManagementView.vue')
            },
            {
                path: '/question',
                component: () => import('../views/admin/management/QuestionManagementView.vue')
            },
            {
                path: '/subject',
                component: () => import('../views/admin/management/SubjectManagementView.vue')
            },
            {
                path: '/student',
                component: () => import('../views/admin/management/StudentManagementView.vue')
            },
            {
                path:'/exampaper',
                component: () => import('../views/admin/management/ExamPaperManagementView.vue')
            },
            {
                path: '/admin/set',
                component: () => import('../views/admin/management/Personal')
            },
            {
                path: '/exam/add',
                component: () => import('../views/admin/exampaper/ExamPaperAdd')
            },
            {
                path: '/exam/edit',
                name: "examEdit",
                component: () => import('../views/admin/exampaper/ExamPaperEdit')
            },
            {
                path: '/exampaperanswer',
                name: "exampaperanswer",
                component: () => import('../views/admin/exampaper/ExamPaperAnswer')
            }

        ]
    },
    {
        path: '/studentMenu',
        component: () => import('../views/student/StudentMenu.vue'),
        redirect:"/student/exam",
        children: [
            {
                path: '/student/exam',
                component: () => import('../views/student/management/ExamManagement')
            },
            {
                path: '/student/set',
                component: () => import('../views/student/management/Personal')
            },
            {
                path:'/student/exampaper',
                name:"examPaper",
                component: () => import('../views/student/exampaper/ExamPaper')
            },
            {
                path: '/student/examPaperDone',
                name:"examPaperDone",
                component: () => import('../views/student/exampaper/ExamPaperDone')
            }
        ]
    },
    {
        path: '/login',
        component: () => import('../views/Login.vue'),
    },
    {
        path: '/',
        redirect: '/login'
    },
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router
