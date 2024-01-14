import axios from "axios";
import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";


export default function AddCampaign() {
    let navigate = useNavigate()
    const [defaultData, setDefaultData] = useState({
        keywords: "",
        towns: []
    });
    const [campaign, setCampaign] = useState({
        campaignName: "",
        keywords: "",
        bidAmount: 0.0,
        campaignFund: 0.0,
        status: false,
        town: "",
        radius: 0.0

    })
  
    const onInputeChange = (e) => {
        setCampaign({ ...campaign, [e.target.name]: e.target.value })
    }
    const onStatusChange = (e) => {
        setCampaign({ ...campaign, status: e.target.checked });
    };

    const onSubmit = async (e) => {
        console.log(campaign)
        e.preventDefault();
        await axios.post("http://localhost:8080/api/campaigns", campaign)
        navigate("/")
    }
    useEffect(() => {
        loadDefaultData();
        setCampaign((prevCampaign) => ({
            ...prevCampaign,
            keywords: defaultData.keywords 
        }));
    }, [defaultData.keywords]);



    const loadDefaultData = async () => {
        const result = await axios.get("http://localhost:8080/api/default-data");
        setDefaultData(result.data);

    };
    const { campaignName, keywords, bidAmount, campaignFund, status, town, radius } = campaign
    return <div className="container">
        <div className="row">
            <div className="row">
                <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
                    <h2>Add new campaign</h2>
                    <form onSubmit={(e) => onSubmit(e)}>
                        <div className="mb-3">
                            <label htmlFor="Name" className="form-label">
                                CampaignName
                            </label>
                            <input
                                type={"text"}
                                className="form-control"
                                placeholder="Enter campaign name"
                                name="campaignName"
                                value={campaignName}
                                onChange={(e) => onInputeChange(e)}
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="Name" className="form-label">
                                Keywords
                            </label>
                            <input
                                type={"text"}
                                className="form-control"
                                placeholder="Enter campaign keywords"
                                name="keywords"
                                value={keywords}
                                onChange={(e) => onInputeChange(e)}

                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="Name" className="form-label">
                                BidAmount
                            </label>
                            <input
                                type={"number"}
                                className="form-control"
                                name="bidAmount"
                                value={bidAmount}
                                onChange={(e) => onInputeChange(e)}
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="Name" className="form-label">
                                CampaignFund
                            </label>
                            <input
                                type={"number"}
                                className="form-control"
                                name="campaignFund"
                                value={campaignFund}
                                onChange={(e) => onInputeChange(e)}
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="Name" className="form-label">
                                Status
                            </label>
                            <input
                                type={"checkbox"}
                                className="form-check-input"
                                value={status}
                                id="status"
                                onChange={(e) => onStatusChange(e)}
                            />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="Town" className="form-label">
                                Town
                            </label>
                            <select
                                className="form-control"
                                name="town"
                                value={town}
                                onChange={(e) => onInputeChange(e)}
                            >
                                <option value="" disabled>Select a town</option>
                                {defaultData.towns.map((option, index) => (
                                    <option key={index} value={option}>{option}</option>
                                ))}

                            </select>
                        </div>
                        <div className="mb-3">
                            <label htmlFor="Name" className="form-label">
                                Radius
                            </label>
                            <input
                                type={"number"}
                                className="form-control"
                                placeholder="Enter radius in kilometrs"
                                name="radius"
                                value={radius}
                                onChange={(e) => onInputeChange(e)}
                            />
                        </div>
                        <button type="sumbmit" className="btn btn-outline-primary">Submit</button>
                        <Link className="btn btn-outline-danger mx-2" to="/">Cancel</Link>
                    </form>
                </div>
            </div>
        </div>
    </div>;
}