import { useDispatch } from "react-redux";
import { updateUnits } from "../redux/unit";
import api from "../api/axios";

const UNIT_AND_SERVICES_URL = "api/unidade/v1/unidades-servicos";

const useRestockUnitsAndServices = () => {
  const dispatch = useDispatch();

  const getUnitsAndServices = async () => {
    let response = await api
      .get(UNIT_AND_SERVICES_URL)
      .then(function (response) {
        let data = response.data;
        dispatch(updateUnits(data));
      })
      .catch(function (error) {
        console.log(error);
      });
  };

  return { getUnitsAndServices };
};

export default useRestockUnitsAndServices;