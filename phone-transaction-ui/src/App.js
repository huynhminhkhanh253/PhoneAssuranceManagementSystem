import logo from './logo.svg';
import './App.css';
import NavBar from './Components/NavBar/NavBar';
import {Navigate, Route, Routes, useLocation} from 'react-router-dom';
import Home from './Components/Home/Home';
import Login from './Components/Login/Login';
import SignUp from './Components/SignUp/SignUp';
import OrderContainer from './Components/Order/OrderContainer';

function App() {
    const location = useLocation();
    const PrivateRoute = ({ children }) => {
        const token = localStorage.getItem('token');
        return token ? children : <Navigate to="/login" />;
    };
  return (
      <div className="App">
            {location.pathname !== '/login' && <NavBar/>}
          <div className='container'>
              <Routes>
                  <Route path='/' element={<Home/>}/>
                  <Route path='/signup' element={<SignUp/>}/>
                  <Route path='/login' element={<Login/>}/>
                  
                  <Route path='/order' 
                  element={
                    <PrivateRoute>
                        <OrderContainer/>
                    </PrivateRoute>
                  }>
                  </Route>

              </Routes>
          </div>
      </div>
  );
}

export default App;
