import {React, useState} from 'react';
import { useNavigate } from 'react-router-dom';
import './SignUp.css';
import { Alert, Dialog } from '@mui/material';

const SignUp = () => {
    const [userName, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleSignup = async () => {
        try {
            let payload = {
                "userName" : userName,
                "password" : password
            }
            const response = await fetch('http://localhost:8081/managerService/user/register', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
            },
            body: JSON.stringify(payload)
          });
          
    
          if (response.ok) {
            try{
              navigate('/login');
            } 
            catch (error) { 
              
            }
          } else {
            const errorData = await response.json();
            alert(errorData);
            console.error('Login failed:', errorData.message);
          }
        } catch (error) {
            alert(error);
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
                        SIGN UP
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
                    <button onClick={handleSignup} className='login_button'>
                        SIGN UP
                    </button>
                </div>
            </div>
        </div>
    );
};

export default SignUp;