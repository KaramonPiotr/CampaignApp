import axios from "axios";
import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";

export default function AddCampaign() {
    const [account, setAccount] = useState()
    useEffect(() => {
       getAccount();
    }, []);
    const getAccount = async () => {
        const result = await axios.get("http://localhost:8080/api/account/balance");
        setAccount(result.data);
    };
    return(
    <div>
        <h2>Account {account} $</h2>
    </div>
    )
}