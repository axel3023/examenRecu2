import React, { useContext } from 'react'
import SignInPage from '../modules/auth/SignInPage'
import AuthContext from '../config/context/auth-context';
import { createBrowserRouter, createRoutesFromElements, Route, RouterProvider } from 'react-router-dom';
import AdminLayout from '../modules/admin/AdminLayout';
import UserPage from '../modules/admin/users/UserPage';
import ClientLayout from '../modules/admin/users/ClientLayout';
import AdminPage from '../modules/admin/users/AdminPage';
const AppRouter = () => {
  const { user } = useContext(AuthContext);
  const rol = localStorage.getItem("rol")
  const router = createBrowserRouter(
    createRoutesFromElements(
      <>
   
        {user.signed && rol == "ADMIN_ROLE" ? (
            <>
              <Route path='/' element={<AdminLayout />}> 
              </Route> <Route path='admin' element={<AdminPage/>} />
              </>
          ) : user.signed && rol == "USER_ROLE" ? (<>
                <Route path='/' element={<UserPage/>} />
                <Route path='user' element={<UserPage/>} />
                </>) : user.signed && rol == "CLIENT_ROLE" ? (<>
                <Route path='client' element={<ClientLayout/>} />
             
            </>) : <Route path='/' element={<SignInPage />} />
        }
        <Route path='/*' element={<>404 NOT FOUND</>} />
      </>)
  );
  return <RouterProvider router={router} />;

}
export default AppRouter