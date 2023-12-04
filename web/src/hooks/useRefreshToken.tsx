import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import jwt from "jwt-decode";
import axios from "../api/axios"
import { getRefreshToken, updateAccessToken, updateRoles } from "../redux/client";

interface jwtDecoded {
  email: string
}

const useRefreshToken = () => {
  const dispatch = useDispatch();

  const refreshToken = localStorage.getItem('refreshToken');
  
  if (refreshToken === null) { 
    return () => {}
  }

  const refreshDecoded : jwtDecoded = jwt(refreshToken);
  
  const email = refreshDecoded.email;

  const refresh = async () => {
    try {
      const response = await axios.put(
        `/auth/refresh/${email}`,
        {},
        {
          withCredentials: true,
          headers: {
            "content-type": "application/json",
            Authorization: `Bearer ${refreshToken}`,
          },
        }
      );
        let data = response.data;
        const roles = jwt(data.accessToken) as { roles: string[] };
        console.log(data.accessToken)
        console.log(data.refreshToken)

        dispatch(updateRoles(roles.roles));
        dispatch(updateAccessToken(data.accessToken));            

        return data.accessToken;
    } catch(error) {
      console.log(error);
      throw error; // Re-throw the error to propagate it to the caller
    }
  }
  return refresh
}

export default useRefreshToken

