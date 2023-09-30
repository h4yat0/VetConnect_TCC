import { axiosPrivate } from "../api/axios";
import { useEffect }from 'react'
import useRefreshToken from "./useRefreshToken";
import { getAccessToken } from "../redux/client";

const useAxiosPrivate = () => {
    const refresh = useRefreshToken();
    
    useEffect(()=>{

        const requestIntercept = axiosPrivate.interceptors.response.use(
            config => {
                if(!config.headers['Authorization']){
                    config.headers["Authorization"] = `Bearer ${getAccessToken}`;
                }
                return config
            }, (error) => Promise.reject(error)
        )

        const reponseIntercept = axiosPrivate.interceptors.response.use(
            response => response, async(error) => {
                const prevRequest = error?.config
                if (error?.response?.status === 403 && !prevRequest?.sent) {
                    prevRequest.sent = true

                    const newAccessToken = await refresh()
                    prevRequest.headers["Authorization"] = `Bearer ${newAccessToken}`;
                    return axiosPrivate(prevRequest);
                }
                return Promise.reject(error)
        });

        return () => {
            axiosPrivate.interceptors.response.eject(requestIntercept);
            axiosPrivate.interceptors.response.eject(reponseIntercept)
        }
        
    }, [refresh])

    return axiosPrivate
}

export default useAxiosPrivate;