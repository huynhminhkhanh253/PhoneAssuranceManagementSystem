import {React, useState} from 'react';
import { useNavigate } from 'react-router-dom';
import './Login.css';
import { Alert, Dialog } from '@mui/material';

const Login = () => {
    const [userName, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [loggedIn, setLoggedIn] = useState(false);
    const [inputToken, setInputToken] = useState('');
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const navigate = useNavigate();

    const handleSignin = async () => {
        try {
            let payload = {
                "userName" : userName,
                "password" : password
            }
            const response = await fetch('http://localhost:8081/managerService/user/login', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
            },
            body: JSON.stringify(payload)
          });
          
    
          if (response.ok) {
            try{
              setLoggedIn(true);
              const input = await response.json();
              setInputToken(input.token);
              //await navigator.clipboard.writeText(input.token);
              //alert('Your token is copied to clipboard!');
              localStorage.setItem('token', input.token);
              navigate('/order');
            } 
            catch (error) { 
              alert(error);
            }
          } else {
            
            const errorData = await response.json();
            console.error('Login failed:', errorData.message);
          }
        } catch (error) {
          console.error('Login failed:', error);
        }
      };
      
    return (
        <div className='login_page'>
            <div className='login_container'>
                <div className='login_logo'>
                    <img src='https://ik.imagekit.io/cx85rn0p5/logo.png?updatedAt=1716451346334'/>
                </div>
                <div>
                    <div className='login_top'>
                        SIGN IN
                    </div>
                    <div >
                        <input className='login_input' placeholder="Username" 
                        type="text"
                        value={userName}
                        onChange={(e) => setUsername(e.target.value)} 
                        />
                    </div>
                    <div style={{marginTop:"25px"}}>
                        <input className="login_input" placeholder="Password" 
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        />
                    </div>
                    <div style={{marginTop:"25px", display:"flex", marginLeft:"30px"}}>
                        <input type="checkbox" style={{width:"20px", height:"20px"}} />
                        <div style={{marginLeft:"10px", color:"#4a4a4a"}}>Remember my Username</div>
                    </div>
                    <button onClick={handleSignin} className='login_button'>
                        SIGN IN
                    </button>
                </div>
            </div>
        </div>
    );
};

export default Login;