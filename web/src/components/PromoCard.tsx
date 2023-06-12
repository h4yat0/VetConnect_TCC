import vetConnectLogo from "../assets/imgs/BannerPromo.png";

export default function PromoCard() {
	

	return (
    <div className="border align-center border-white bg-white">
      <div className="w-full  bg-white grid place-items-center background-size:cover;">
        <img
          src={vetConnectLogo}
          alt="tailwind logo"
          className="rounded-xl w-screen"
        />
      </div>
    </div>
  );
}