import React from 'react'

function Register() {
    const baseUrl = process.env.REACT_APP_API_URL

    console.log(baseUrl , "Hello ")
  return (
    <div className='main-div'> 
    {baseUrl}
    </div>
  )
}

export default Register